package com;/*
 * Copyright (c) 1998-$Date: 2013-12-19 15:26:51 -0800 (Thu, 19 Dec 2013) $ TIBCO Software Inc.
 * All rights reserved.
 * TIB/Rendezvous is protected under US Patent No. 5,187,787.
 * For more information, please contact:
 * TIBCO Software Inc., Palo Alto, California, USA
 *
 */

/*
 * tibrvlisten - generic Rendezvous subscriber
 *
 * This program listens for any number of messages on a specified
 * set of subject(s).  Message(s) received are printed.
 *
 * Some platforms require proper quoting of the arguments to prevent
 * the command line processor from modifying the command arguments.
 *
 * The user may terminate the program by typing Control-C.
 *
 * Optionally the user may specify communication parameters for
 * tibrvTransport_Create.  If none are specified, default values
 * are used.  For information on default values for these parameters,
 * please see the TIBCO/Rendezvous Concepts manual.
 *
 *
 * Examples:
 *
 * Listen to every message published on subject a.b.c:
 *  java tibrvlisten a.b.c
 *
 * Listen to every message published on subjects a.b.c and x.*.Z:
 *  java tibrvlisten a.b.c "x.*.Z"
 *
 * Listen to every system advisory message:
 *  java tibrvlisten "_RV.*.SYSTEM.>"
 *
 * Listen to messages published on subject a.b.c using port 7566:
 *  java tibrvlisten -service 7566 a.b.c
 *
 */

import com.tibco.tibrv.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tibrvlisten implements TibrvMsgCallback
{

    String service = null;
    String network = null;
    String daemon  = null;
    List<String> subjects ;

    public Tibrvlisten(String service, String network, String daemon, List<String> subjects){
        this.service=service;
        this.network=network;
        this.daemon=daemon;
        this.subjects = subjects;
        try
        {
            Tibrv.open(Tibrv.IMPL_NATIVE);
        }
        catch (TibrvException e)
        {
            System.err.println("Failed to open Tibrv in native implementation:");
            e.printStackTrace();
            System.exit(0);
        }
        TibrvTransport transport = null;
        try
        {
            transport = new TibrvRvdTransport(service,network,daemon,null);
        }
        catch (TibrvException e)
        {
            System.err.println("Failed to create TibrvRvdTransport:");
            e.printStackTrace();
            System.exit(0);
        }
        if (subjects!=null){
            for (String subject:subjects){
                try
                {
                    new TibrvListener(Tibrv.defaultQueue(),
                            this,transport,subject,null);
                    System.err.println("Listening on: "+subject);
                }
                catch (TibrvException e)
                {
                    System.err.println("Failed to create listener:");
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        }
        // dispatch Tibrv events
        while(true)
        {
            try
            {
                Tibrv.defaultQueue().dispatch();
            }
            catch(TibrvException e)
            {
                System.err.println("Exception dispatching default queue:");
                e.printStackTrace();
                System.exit(0);
            }
            catch(InterruptedException ie)
            {
                System.exit(0);
            }
        }
    }
    public Tibrvlisten(String args[])
    {
        // parse arguments for possible optional
        // parameters. These must precede the subject
        // and message strings
        int i = get_InitParams(args);

        // we must have at least one subject
        if (i >= args.length)
            usage();

        // open Tibrv in native implementation
        try
        {
            Tibrv.open(Tibrv.IMPL_NATIVE);
        }
        catch (TibrvException e)
        {
            System.err.println("Failed to open Tibrv in native implementation:");
            e.printStackTrace();
            System.exit(0);
        }

        // Create RVD transport
        TibrvTransport transport = null;
        try
        {
            transport = new TibrvRvdTransport(service,network,daemon,null);
        }
        catch (TibrvException e)
        {
            System.err.println("Failed to create TibrvRvdTransport:");
            e.printStackTrace();
            System.exit(0);
        }

        // Create listeners for specified subjects
        while (i < args.length)
        {
            // create listener using default queue
            try
            {
                new TibrvListener(Tibrv.defaultQueue(),
                        this,transport,args[i],null);
                System.err.println("Listening on: "+args[i]);
            }
            catch (TibrvException e)
            {
                System.err.println("Failed to create listener:");
                e.printStackTrace();
                System.exit(0);
            }
            i++;
        }

        // dispatch Tibrv events
        while(true)
        {
            try
            {
                Tibrv.defaultQueue().dispatch();
            }
            catch(TibrvException e)
            {
                System.err.println("Exception dispatching default queue:");
                e.printStackTrace();
                System.exit(0);
            }
            catch(InterruptedException ie)
            {
                System.exit(0);
            }
        }
    }

    public void onMsg(TibrvListener listener, TibrvMsg msg)
    {
        System.out.println((new Date()).toString()+
                ": subject="+msg.getSendSubject()+
                ", reply="+msg.getReplySubject()+
                ", message="+msg.toString()
        );
        System.out.flush();
    }

    // print usage information and quit
    void usage()
    {
        System.err.println("Usage: java tibrvlisten [-service service] [-network network]");
        System.err.println("            [-daemon daemon] <subject-list>");
        System.exit(-1);
    }

    int get_InitParams(String[] args)
    {
        int i=0;
        while(i < args.length-1 && args[i].startsWith("-"))
        {
            if (args[i].equals("-service"))
            {
                service = args[i+1];
                i += 2;
            }
            else
            if (args[i].equals("-network"))
            {
                network = args[i+1];
                i += 2;
            }
            else
            if (args[i].equals("-daemon"))
            {
                daemon = args[i+1];
                i += 2;
            }
            else
                usage();
        }
        return i;
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

        new Tibrvlisten(service,network,daemon,subjects);
    }

}
