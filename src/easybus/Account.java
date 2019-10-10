package easybus;

public class Account {

    private String username;
    private String password;
    private String role;
    private String path;
    private String note;

    public Account(String username,String password,String role,String note,String path)
    {
        this.username=username;
        this.password=password;
        this.role=role;
        this.path=path;
        this.note=note;
    }
    
    public void setUsername(String username)
    {
        this.username=username;
    }
    public void setpassword(String password)
    {
        this.password=password;
    }
    public String getUsername()
    {
        return this.username;
    }
    public String getPassword()
    {
        return this.password;
    }
    public String getRole()
    {
        return this.role;
    }
    public String toString()
    {
        return "Username:"+this.username+" Password:"+this.password+" Role:"+this.role;
    }
    public String getPath()
    {
        return this.path;
    }
    public String getNotes()
    {
        return this.note;
    }
    public void setPath(String path)
    {
        this.path=path;
    }
    public void setNotes(String notes)
    {
        this.note=notes;
    }
}
