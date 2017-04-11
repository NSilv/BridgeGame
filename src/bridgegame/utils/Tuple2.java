/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridgegame.utils;

/**
 *
 * @author s01412
 */
public class Tuple2<A,B> {
    
    public final A first;
    public final B second;
    
    public Tuple2(A first, B second){
        this.first = first;
        this.second = second;
    }
}
