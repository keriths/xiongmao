package com.xm.job;
import com.tibco.tibrv.*;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.EquipmentStatusServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tibrvlisten implements TibrvMsgCallback
{
    private EquipmentStatusServiceImpl equipmentStatusService;

    private String service = null;
    private String network = null;
    private String daemon  = null;
    private  List<String> subjects ;
    public Tibrvlisten(){

    }
    public void init(){
        LogUtils.error(Tibrvlisten.class,"Tibrvlisten init .......");
        final TibrvMsgCallback callback = this;
        new Thread(){
            @Override
            public void run() {
                try
                {
                    Tibrv.open(Tibrv.IMPL_NATIVE);
                }
                catch (TibrvException e)
                {
                    LogUtils.error(Tibrvlisten.class, "Tibrvlisten init .......Failed to open Tibrv in native implementation:", e);
//                    System.exit(0);
                }
                TibrvTransport transport = null;
                try
                {
                    transport = new TibrvRvdTransport(service,network,daemon,null);
                }
                catch (TibrvException e)
                {
                    LogUtils.error(Tibrvlisten.class,"Tibrvlisten init .......Failed to create TibrvRvdTransport::",e);
//                    System.exit(0);
                }
                if (subjects!=null){
                    for (String subject:subjects){
                        try
                        {
                            new TibrvListener(Tibrv.defaultQueue(), callback,transport,subject,null);
                            //LogUtils.info(Tibrvlisten.class,"Listening on: "+subject);
                        }
                        catch (TibrvException e)
                        {
                            LogUtils.error(Tibrvlisten.class, "Tibrvlisten init .......Failed to create listener:", e);
//                            System.exit(0);
                        }
                    }
                }
                //LogUtils.info(Tibrvlisten.class,"Tibrvlisten init ....... successed");
                // dispatch Tibrv events
                while(true)
                {
                    try
                    {
                        Tibrv.defaultQueue().dispatch();
                    }
                    catch(TibrvException e)
                    {
                        LogUtils.error(Tibrvlisten.class,"Tibrvlisten init .......Exception dispatching default queue:",e);
//                        System.exit(0);
                    }
                    catch(InterruptedException ie)
                    {
                        LogUtils.error(Tibrvlisten.class,"Tibrvlisten init .......Exception InterruptedException:",ie);
//                        System.exit(0);
                    }
                }
            }
        }.start();
    }

    public void onMsg(TibrvListener listener, TibrvMsg msg)
    {
        String msgData = msg.toString();
//        //LogUtils.info(Tibrvlisten.class,msgData);
        equipmentStatusService.equipmentStatusUpdate(msgData);

//        System.out.println((new Date()).toString()+
//                ": subject="+msg.getSendSubject()+
//                ", reply="+msg.getReplySubject()+
//                ", message="+msg.toString()
//        );
//        System.out.flush();
    }


    public static void main(String args[])
    {
//        new tibrvlisten(args);
        String service = "7580";
        String network = "192.168.1.201";
        String daemon  = null;
        List<String> subjects = new ArrayList<String>();
        subjects.add("mysubject");
        //.\tibrvsend.exe -service 7580 -network 127.0.0.1  mysubject HelloWorld

        Tibrvlisten t = new Tibrvlisten();
        t.service=service;
        t.network=network;
        t.daemon=daemon;
        t.subjects=subjects;
        t.init();
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getDaemon() {
        return daemon;
    }

    public void setDaemon(String daemon) {
        this.daemon = daemon;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public EquipmentStatusServiceImpl getEquipmentStatusService() {
        return equipmentStatusService;
    }

    public void setEquipmentStatusService(EquipmentStatusServiceImpl equipmentStatusService) {
        this.equipmentStatusService = equipmentStatusService;
    }
}
