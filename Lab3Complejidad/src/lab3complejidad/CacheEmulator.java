/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3complejidad;

import java.util.ArrayList;

/**
 * Cache imitation
 * @author Laboratorio
 * @param <E> Calculus return type
 */
public class CacheEmulator <E extends Comparable>{
    
    private ArrayList<Calculus> calc_registers;
    private int max_size;
    
    public CacheEmulator(int max_size) {
        calc_registers = new ArrayList<Calculus>();
        this.max_size = max_size;
    }
    
    public void insert(String method_name, E result, String... params)
    {
        if(calc_registers.size()>= max_size)
            calc_registers.remove(0);
        
        calc_registers.add(new Calculus<E>(method_name, result, params));
        //System.out.println(calc_registers);
    }
    
    public E rememberResult(String method_name, String... params)
    {
        int index = calc_registers.indexOf(new Calculus(method_name, params));
        
        if(index == -1)
            return null;
        
        return (E)calc_registers.get(index).result;
    }
    
    //auxiliar class
    
    /**
     * the result of one method
     * @param <T> the method return type
     */
    private class Calculus <T extends Comparable> {
        
        protected String method_name;
        protected T result;
        protected String[] parameters;

        public Calculus(String method_name, T result, String ... parameters) {
            this.method_name = method_name;
            this.result = result;
            this.parameters = parameters;
        }

        public Calculus(String method_name, String ... parameters) {
            this.method_name = method_name;
            this.parameters = parameters;
        }
        
        @Override
        public String toString()
        {
            return result.toString();
        }
        
        @Override
        public boolean equals(Object object)
        {
            Calculus other = (Calculus)object;
            
            if(parameters.length != other.parameters.length)
                    return false;
            
            for (int i = 0; i < parameters.length; i++)
                if(!parameters[i].equals(other.parameters[i]))
                    return false;
            
            return this.method_name.equals(other.method_name);
        }
        
    }
            

    //getters and setters
    
    public ArrayList<Calculus> getCalc_registers() {
        return calc_registers;
    }

    public void setCalc_registers(ArrayList<Calculus> calc_registers) {
        this.calc_registers = calc_registers;
    }

    public int getMax_size() {
        return max_size;
    }

    public void setMax_size(int max_size) {
        this.max_size = max_size;
    }
    
    
}
