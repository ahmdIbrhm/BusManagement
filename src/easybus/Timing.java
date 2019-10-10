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
public class Timing {
    private String studentId;
    private String studentName;
    private String beginTime;
    private String endTime;
    public Timing(String studentId,String studentName,String beginTime,String endTime)
    {
        this.studentId=studentId;
        this.beginTime=beginTime;
        this.endTime=endTime;
        this.studentName=studentName; 
    }
    public String getBeginTime()
    {
        return this.beginTime;
    }
    public String getEndTime()
    {
        return this.endTime;
    }
    public String getStdId()
    {
        return this.studentId;
    }
    public String getstdName()
    {
        return this.studentName;
    }
}
