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
public class Tuple3<A,B,C> {
    public final A first;
    public final B second;
    public final C third;
    
    public Tuple3(A first, B second, C third){
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
