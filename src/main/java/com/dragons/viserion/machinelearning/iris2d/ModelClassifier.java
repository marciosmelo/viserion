/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dragons.viserion.machinelearning.iris2d;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;

/**
 *
 * @author Marcio
 */
public class ModelClassifier {
    private Attribute petallength;
    private Attribute petalwidth;

    private ArrayList attributes;
    private ArrayList classVal;
    private Instances dataRaw;


    public ModelClassifier() {
        petallength = new Attribute("petallength");
        petalwidth = new Attribute("petalwidth");
        attributes = new ArrayList();
        classVal = new ArrayList();
        classVal.add("Iris-setosa");
        classVal.add("Iris-versicolor");
        classVal.add("Iris-virginica");

        attributes.add(petallength);
        attributes.add(petalwidth);

        attributes.add(new Attribute("class", classVal));
        dataRaw = new Instances("TestInstances", attributes, 0);
        dataRaw.setClassIndex(dataRaw.numAttributes() - 1);
    }

    
    public Instances createInstance(double petallength, double petalwidth, double result) {
        dataRaw.clear();
        double[] instanceValue1 = new double[]{petallength, petalwidth, 0};
        dataRaw.add(new DenseInstance(1.0, instanceValue1));
        return dataRaw;
    }


    public String classifiy(Instances insts, String path) {
        String result = "Not classified!!";
        Classifier cls = null;
        try {
            cls = (MultilayerPerceptron) SerializationHelper.read(path);
            result = (String) classVal.get((int) cls.classifyInstance(insts.firstInstance()));
        } catch (Exception ex) {
            Logger.getLogger(ModelClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }


    public Instances getInstance() {
        return dataRaw;
    }
}
