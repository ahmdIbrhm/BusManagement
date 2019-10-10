/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easybus;

public class Employee {
    private String id;
    private String name;
    private String phone_nbr;
    private Account account;
    private String role;
    public Employee(String id,String name,String username,String phone_nbr,String role)
    {
        this.id=id;
        this.name=name;
        this.phone_nbr=phone_nbr;
        this.account=new Account(name,id,role,null,null);
        this.role=role;
    }
    public Employee(String id,String name,String phone,String role)
    {
        this.id=id;
        this.name=name;
        this.phone_nbr=phone;
        this.role=role;
    }
    public String getRole()
    {
        return this.role;
    }
    public Account getAccount()
    {
        return this.account;
    }
    public void setAccount(String username,String password,String note,String path)
    {
        this.account=new Account(username,password,"E",note,path);
    }
    public String getId()
    {
        return this.id;
    }
    public void setId(String id)
    {
        this.id=id;
    }
    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getPhoneNbr()
    {
        return this.phone_nbr;
    }
    public void setPhoneNbr(String phone_nbr)
    {
        this.phone_nbr=phone_nbr;
    }
}
