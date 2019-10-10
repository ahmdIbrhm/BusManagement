
package easybus;

public class Group {
    private int nbOfStudents;
    private String time;
    public Group(int nbOfStudents,String time)
    {
        this.nbOfStudents=nbOfStudents;
        this.time=time;
    }
    public int getNbOfStudents()
    {
        return this.nbOfStudents;
    }
    public String getTime()
    {
        return this.time;
    }
    public void setNumberOfStudent(int s)
    {
        this.nbOfStudents=s;
    }
    public String toString()
    {
        return "Time:"+this.time+" #Students:"+this.nbOfStudents;
    }
}
