package com.neuroconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class NeurogenesisPipeline {
    static private Map<String, Consumer<Map>> nameToMethod;
    List actions;

    NeurogenesisPipeline(List acts) {
        initializeMap();
        if(validateActions(acts)) {
            actions = acts;
        }
        else
        {
            throw new RuntimeException("Cannot validate actions!");
        }
    }

    public boolean run() {
        for(Object obj : actions) {
            Map m = (Map)obj;

            String t = (String)m.get("Type");
            Map params = (Map)m.get("ActionParams");

            nameToMethod.get(t).accept(params);
        }

        return true;
    }

    private boolean trainAction(Map params) {
        System.out.println("Running train action! Params: ");
        System.out.println(params);

        return true;
    }

    private boolean testAction(Map params) {
        System.out.println("Running test action! Params: ");
        System.out.println(params);

        return true;
    }

    private boolean damageAction(Map params) {
        System.out.println("Running damage action! Params: ");
        System.out.println(params);

        return true;
    }

    private boolean neurogenesisAction(Map params) {
        System.out.println("Running neurogenesis action! Params: ");
        System.out.println(params);

        return true;
    }

    private boolean topoCompareAction(Map params) {
        System.out.println("Running topology compare action! Params: ");
        System.out.println(params);

        return true;
    }

    private void initializeMap() {
        nameToMethod = new HashMap<>();

        nameToMethod.put("Train", this::trainAction);
        nameToMethod.put("Test", this::testAction);
        nameToMethod.put("Damage", this::damageAction);
        nameToMethod.put("Neurogenesis", this::neurogenesisAction);
        nameToMethod.put("CompareTopology", this::topoCompareAction);
    }

    private boolean validateActions(List acts) {
        for(Object obj : acts) {
            Map m = (Map)obj;
            String t = (String)m.get("Type");

            if(!nameToMethod.containsKey(t)) {
                return false;
            }
        }

        return true;
    }


}
