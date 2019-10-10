/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easybus;

/**
 *
 * @author Ali Haidar
 */
public class Payment {
    private String id;
    private String name;
    private boolean pay;
    public Payment(String id,String name,boolean pay)
    {
        this.id=id;
        this.name=name;
        this.pay=pay;
    }
    public String getId()
    {
        return this.id;
    }
    public String getName()
    {
        return this.name;
    }
    public boolean getPay()
    {
        return this.pay;
    }
}
