/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easybus;

import java.util.ArrayList;

public class ReturnedValues {
    private int loss;
    private ArrayList<Group> array;
    public ReturnedValues(int loss,ArrayList<Group> array)
    {
        this.loss=loss;
        this.array=array;
    }
    
    public int getLoss()
    {
        return this.loss;
    }
    public ArrayList<Group> getArray()
    {
        return this.array;
    }
}
