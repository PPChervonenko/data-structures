package com.datastructures.trees.genealogicalTree.researchRootGenealogicalTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree {
    private List<People> people = new ArrayList<>();
   private NewPeople newPeople = new NewPeople();
    private Map<NewPeople,String> tree = new HashMap();

    public  void  addChildren(List<People> children){
        people.addAll(children);
    }
    public  void  addChildren(People child){
        People newChild = new People(child.getName());
                newChild.setChildren(child.getChildren());
        people.add(newChild);
    }
    public void addChild(String name) {
        People newChild = new People(name);
           newChild.setName(name);
        people.add(newChild);
    }

    public void searchRoot(){
        System.out.println("---------------------------------------------------");
        for (People i : people) tree.put( EditPeople(i), String.valueOf(i.getChildren()));
        System.out.println(tree);

        tree.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });

    }
    public NewPeople EditPeople(People people){
        if(people!=null){
            newPeople.setParent(people);
            newPeople.setChildren(people.getChildren());
        }
        return newPeople;
    }

    public List<People> getChildren() {
        return people;
    }

}
