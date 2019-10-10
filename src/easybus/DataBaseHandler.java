/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easybus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseHandler {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DataBaseHandler() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Bus;integratedSecurity=true;");
            st = con.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getData() {
        try {
            rs = st.executeQuery("select * from Student");
            while (rs.next()) {
                String stud_id = rs.getString(1);
                String stud_name = rs.getString(2);
                String username = rs.getString(3);
                String phone_nb = rs.getString(4);
                String address = rs.getString(5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Employee getEmployee(String user) {
        try {
            rs = st.executeQuery("select * from Employee where username='" + user + "'");
            rs.next();
            Employee employee = new Employee(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5));
            rs=st.executeQuery("select * from Account where username='"+user+"'");
            rs.next();
            employee.setAccount(rs.getString(1), rs.getString(2),rs.getString(4),rs.getString(5));
            return employee;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Student getStudent(String user) {
        try {
            rs = st.executeQuery("select * from Student where username='" + user + "'");
            if (rs.next()) {
                Student s = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                rs=st.executeQuery("select * from Account where username='"+user+"'");
                rs.next();
                s.setAccount(rs.getString(1), rs.getString(2),rs.getString(4),rs.getString(5));
                return s;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String addStudent(Student student) {
        try {
            st.execute("insert into Student values('" + student.getId() + "','" + student.getName() + "','" + student.getPhoneNumber() + "','" + student.getAddress() + "','"+student.getName()+student.getId()+"')");     
            return "true";
        } catch (SQLException ex) {
            //Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            String exceptionString=ex.toString();
            String [] error=exceptionString.split(" ");
            if(Arrays.asList(error).contains("PRIMARY"))
                return "Wrong Id";
            else if(Arrays.asList(error).contains("CHECK"))
                return "Fill all fields correctly";
        }
        return "Name is unique because of the username";
    }

    public Account getAccount(String username) {
        try {
            rs = st.executeQuery("select * from Account where username='" + username + "'");
            if (rs.next()) {
                Account a = new Account(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5));
                return a;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ArrayList<Group> getTimings(String state)
    {
        ArrayList<Group> groups=new ArrayList<>();
        try {
            rs=st.executeQuery("exec proc_make_groups '"+state+"'");
            while(rs.next())
            {
                
                groups.add(new Group(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }
    
    public ArrayList<Group> getTimingsInThisDay(String state,String day)
    {
        ArrayList<Group> groups=new ArrayList<>();
        try {
            rs=st.executeQuery("exec proc_make_groups_days '"+state+"','"+day+"'");
            while(rs.next())
            {
                
                groups.add(new Group(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }
    
    public ArrayList<String> getBuses()
    {
        ArrayList<String> buses=new ArrayList<>();
        try {
            rs=st.executeQuery("select busName from Bus");
            while(rs.next())
                buses.add(rs.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buses;
    }
    public String updateEmployeeInfo(Employee e)
    {
        try {
            st.executeUpdate("update employee set employeeName='"+e.getName()+"',phoneNb='"+e.getPhoneNbr()+"' where employeeId='"+e.getId()+"'");
            return "true";
        } catch (SQLException ex) {
            
            String exceptionString=ex.toString();
            String [] error=exceptionString.split(" ");
            if(Arrays.asList(error).contains("CHECK"))
                return "Fill all fields ";
        }
        return "";
    }
    public boolean updateUsername(String oldUsername,String newUsername)
    {
        try {
            st.executeUpdate("update Account set username='"+newUsername+"' where username='"+oldUsername+"'");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public ArrayList<Timing> getTimingsPerDay(String day)
    {
        ArrayList<Timing> timings=new ArrayList<>();
        try {
            rs=st.executeQuery("exec proc_get_Timings '"+day+"'");
            while(rs.next())
            {
                timings.add(new Timing(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timings;
    }
     public String updateStudentInfo(Student s)
    {
        try {
            st.executeUpdate("update student set studentName='"+s.getName()+"',phoneNb='"+s.getPhoneNumber()+"' where studentId='"+s.getId()+"'");
            return "true";
        } catch (SQLException ex) {
            String exceptionString=ex.toString();
            String [] error=exceptionString.split(" ");
            if(Arrays.asList(error).contains("CHECK"))
                return "Fill all fields correctly";
        }
        return "";
    }
    
    public ArrayList<String> getSchedule(Student s)
    {
        ArrayList<String> data=new ArrayList<>();
        try 
        {
            rs=st.executeQuery("exec proc_student_schedule '"+s.getId()+"'");
            while(rs.next())
            {
                String[] res1=rs.getString(2).split(":");
                String[] res2=rs.getString(3).split(":");
                String begin=res1[0]+":"+res1[1];
                String end=res2[0]+":"+res2[1];
                data.add(rs.getString(1)+"/"+begin+"/"+end);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public void changePassword(String username,String s)
    {
        try {
            st.executeUpdate("update Account set password='"+s+"'where username='"+username+"'");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String addEmployee(Employee e)
    {
        try {
            st.executeUpdate("insert into employee values('"+e.getId()+"','"+e.getName()+"','"+e.getName()+"','"+e.getPhoneNbr()+"','"+e.getRole()+"')");
            return "true";
        } 
        catch (SQLException ex) {
            System.out.println(ex);
            String exceptionString=ex.toString();
            String [] error=exceptionString.split(" ");
            if(Arrays.asList(error).contains("PRIMARY"))
                return "Wrong Id";
            else if(Arrays.asList(error).contains("CHECK"))
                return "Fill all fields correctly";
        }
        return "Name is unique because of the username";
    }
    public void insertTimings(Student s,String day,String begin,String end)
    {
        try {
            rs=st.executeQuery("exec proc_getTiming '"+day+"','"+begin+"','"+end+"'");
            rs.next();
            int id=rs.getInt(1);
            st.executeUpdate("insert into take values('"+s.getId()+"',"+id+")");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void fillTimings()
    {
        try {
            String [] days={"Monday","Tuesday","Wednesday","Thursday","Friday"};
            String [] begins={"08:00","09:00","10:00"};
            String [] ends={"12:00","13:00","14:00","15:00"};
            st.executeUpdate("DBCC CHECKIDENT(timings,RESEED,0)");
            for(int i=0;i<days.length;i++)
            {
                for(int j=0;j<begins.length;j++)
                {
                    for(int z=0;z<ends.length;z++)
                    {
                        try {
                            st.executeUpdate("insert into Timings values('"+days[i]+"','"+begins[j]+"','"+ends[z]+"')");
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Payment> getPayements(int month)
    {
        ArrayList<Payment> payments=new ArrayList<>();
        try {
            rs=st.executeQuery("select * from check_payment("+month+")");
            while(rs.next())
            {
                boolean b=true;
                if(rs.getInt(3)==0)
                    b=false;
                payments.add(new Payment(rs.getString(1),rs.getString(2),b));
            }
        } catch (SQLException ex) {
            System.out.println(payments.size());
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return payments;
    }
    public void addPayment(int month ,String id)
    {
        try {
            st.executeUpdate("insert into Payments values('"+id+"',"+month+")");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addPath(String username,String path)
    {
        try {
            st.executeUpdate("update Account set path='"+path+"'where username='"+username+"'");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addNotes(String username,String note)
    {
        try {
            st.executeUpdate("update Account set notes='"+note+"'where username='"+username+"'");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void clearNotes(String username)
    {
        try {
            st.executeUpdate("update Account set notes="+null+" where username='"+username+"'");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    public void insertBus(int number)
    {
        String string="bus"+number;
        try {
            st.executeUpdate("insert into Bus values('"+string+"')");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getNumberOfBuses()
    {
        try {
            rs=st.executeQuery("Select count(*) as number from Bus");
            rs.next();
            int number=rs.getInt("number");
            return number;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public void clearProject()
    {
        try {
            st.executeUpdate("exec proc_clear_All_tables");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Employee> getEmployees()
    {
        try {
            ArrayList<Employee> array=new ArrayList<>();
            rs=st.executeQuery("select * from employee where role!='A'");
            while(rs.next())
            {
                array.add(new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));  
            }
            return array;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void addAdmin(String username,String password)
    {
        try {
            st.executeUpdate("insert into Employee values('"+password+"','"+username+"','"+username+"',NULL,'A')");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean emptyDB()
    {
        try 
        {
            rs=st.executeQuery("select * from employee");
            if(rs.next())
                return false;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public void setMax(int max)
    {
        try {
            st.executeUpdate("insert into max values('"+max+"')");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getMax()
    {
        try 
        {
            rs=st.executeQuery("select * from max");
            if(rs.next())
                return rs.getInt(1);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public String [] getMonthsOfPayments()
    {
        ArrayList<Integer> array=new ArrayList<>();
        try {
            rs=st.executeQuery("Select distinct month from Payments");
            while(rs.next())
            {
                array.add(rs.getInt(1));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        String str[]=new String[array.size()];
        for(int i=0;i<array.size();i++)
        {
            str[i]=String.valueOf(array.get(i));
        }
        return str;
    }
    
}
