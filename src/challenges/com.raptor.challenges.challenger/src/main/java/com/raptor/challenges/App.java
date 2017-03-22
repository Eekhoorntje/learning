package com.raptor.challenges;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if(args.length == 0)
        {
        	usage();
        	return;
        }
        
        for(int i = 0; i < args.length; i++)
        {
        	switch(args[i])
        	{
        		case("-factorial") :
        			if(i != args.length-1)
        			{
        				i++;
        				try
        				{
        					
        					Long facParam = Long.parseLong(args[i]);
        					System.out.println(Long.toString(factorialChallenge(facParam)));	
        				}
        				catch(NumberFormatException e)
        				{
        					e.printStackTrace();
        					usage();
        					return;
        				}
        			}
        		break;
        	}
        }
    }
    
    public static void usage()
    {
    	StringBuffer buff = new StringBuffer();
    	buff.append("Welcome to the challenger.  Acceptable arguments as follows:");
    	buff.append("\n\n-?:\t\tPrint Usage");
    	buff.append("\n-factorial <value> :\tPrint Factorial value of parameter.");
    	System.out.println(buff.toString());
    }
    
    public static long factorialChallenge(long val)
    {
    	if(val > 1)
    	{
    		return val * factorialChallenge(val - 1);
    	}
    	else
    	{
    		return val;
    	}
    }
}
