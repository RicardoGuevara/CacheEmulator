/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3complejidad;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author Laboratorio
 */
public class Lab3Complejidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int memory_size = 1000;
        CacheEmulator<BigDecimal> cache_uno = new CacheEmulator<>(memory_size);
        CacheEmulator<BigDecimal> cache_dos = new CacheEmulator<>(memory_size);
        CacheEmulator<BigDecimal> cache_tre = new CacheEmulator<>(memory_size);
        
        String testvalue = "1000";
        BigDecimal bd;
        
        for (int i = 1; i <= Integer.parseInt(testvalue); i++) {
            System.gc();
            System.out.println( CBRusingCache(new BigDecimal(testvalue), new BigDecimal(String.valueOf(i)), cache_uno, cache_dos, cache_tre) );
        }
        /*
        System.out.println("__________ no recursivo");
        
        for (int i = 1; i <= Integer.parseInt(testvalue); i++) {
        System.out.println( CBnoRecursive(new BigDecimal(testvalue), new BigDecimal(String.valueOf(i))) );
        }
        */
    }
    
    static BigDecimal CBRusingCache(BigDecimal n, BigDecimal k, CacheEmulator<BigDecimal> cache_mod1, CacheEmulator<BigDecimal> cache_mod2, CacheEmulator<BigDecimal> cache_mod3)
    {
        BigDecimal flash_back = cache_mod1.rememberResult("CBRusingCache", n.toString(), k.toString());
        if(flash_back != null) 
            return flash_back;
        
        flash_back = cache_mod2.rememberResult("CBRusingCache", n.toString(), k.toString());
        if(flash_back != null) 
            return flash_back;
        
        flash_back = cache_mod3.rememberResult("CBRusingCache", n.toString(), k.toString());
        if(flash_back != null) 
            return flash_back;
        
        if(k.equals(n) || k.equals(BigDecimal.ZERO))
            return BigDecimal.ONE;
        
        BigDecimal part1 = CBRusingCache(n.subtract(BigDecimal.ONE), k.subtract(BigDecimal.ONE), cache_mod1,cache_mod2, cache_mod3);
        BigDecimal part2 = CBRusingCache(n.subtract(BigDecimal.ONE), k, cache_mod1, cache_mod2, cache_mod3);
        BigDecimal result = part1.add(part2);
        
        cache_mod1.insert("CBRusingCache", part1, n.subtract(BigDecimal.ONE).toString(), k.subtract(BigDecimal.ONE).toString());
        cache_mod2.insert("CBRusingCache", part2, n.subtract(BigDecimal.ONE).toString(), k.toString());
        cache_mod3.insert("CBRusingCache", result, n.toString(), k.toString());
        
        return result;
    }
    
    static BigDecimal CBrecursive(BigDecimal n, BigDecimal k)
    {
        if(k.equals(n) || k.equals(BigDecimal.ZERO))
            return BigDecimal.ONE;
        
        return CBrecursive(n.subtract(BigDecimal.ONE), k.subtract(BigDecimal.ONE))
                .add( CBrecursive(n.subtract(BigDecimal.ONE), k) );
    }
    
    static BigDecimal CBnoRecursive(BigDecimal n, BigDecimal k)
    {
        return factorial(n).divide( factorial(k).multiply( factorial( n.subtract(k) ) ) );
    }
    
    static BigDecimal factorial(BigDecimal num)
    {
        if(num.equals(BigDecimal.ZERO))
            return BigDecimal.ONE;
        
        return num.multiply(factorial(num.subtract(BigDecimal.ONE)));
    }
}
