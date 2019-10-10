package easybus;

import java.util.ArrayList;

public class Student {

    private String id;
    private String name;
    private String phoneNumber;
    private String address;
    private Account account;
    private ArrayList<Timing> program;
    
    public Student(String id, String name) {
        
        this.id = id;
        this.name = name;
    }

    public Student(String id, String name, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.program=new ArrayList<Timing>();
    }

    public Student(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.program=new ArrayList<Timing>();
    }
    
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber=phoneNumber;
    }
    public void setAddress(String address)
    {
        this.address=address;
    }
    public void setAccount(String username,String password,String note,String path)
    {
        this.account=new Account(username, password, "S",note,path);
    }
    public String getId()
    {
        return this.id;
    }
    public String getName()
    {
        return this.name;
    }
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    public String getAddress()
    {
        return this.address;
    }
    public Account getAccount()
    {
        return this.account;
    }
    public void addTake(Timing timing)
    {
        this.program.add(timing);
    }
    public void setName(String s)
    {
        this.name=s;
    }
}
