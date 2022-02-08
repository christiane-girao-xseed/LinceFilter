package HLegacy;
import java.io.*;

public class XseedFile
{
	public FileOutputStream outFile;
	public FileInputStream inpFile;
	public PrintStream out;
	public BufferedReader inp;
	public String openType;		    	
	public String openStatus;
    public String command = "";
    private int buffSize = 1048576;


	public void open(String pFileName, String pOpenType)
	{	
		try
	 	{
	 		openType = pOpenType;
			if (openType.equals("INPUT") == true)
			{
				//verificar se arquivo existe
				inpFile = new FileInputStream(pFileName);
        		inp= new BufferedReader(new InputStreamReader(inpFile), buffSize); 			
			}
			else if (openType.equals("OUTPUT") == true)
			{
				outFile = new FileOutputStream(pFileName, false);
      			out = new PrintStream( new BufferedOutputStream (outFile, buffSize) );		
			}
			else if (openType.equals("APPEND") == true)
			{
				outFile = new FileOutputStream(pFileName, true);
      			out = new PrintStream( new BufferedOutputStream (outFile, buffSize) ); 
						
			}
			openStatus = "";
		}
		catch (IOException e)
		{
			e.printStackTrace();
			openStatus = e.getMessage();
		}	

	}
	public void close()
	{
		try
		{
			if (openType.equals("INPUT") == true)
			{
				inp.close();
					inpFile.close();        				
			}
			else if ((openType.equals("OUTPUT") == true) ||
				     (openType.equals("APPEND") == true))		
			{
				out.close();
				outFile.close();      	
			}	
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}		
	}
	public String read()
	{
		try
		{
			return(inp.readLine());
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return("");
		}	
	}
	public void write(String pBuffer)
	{
		try
		{
			out.println(pBuffer);
		}	
		catch (Exception e)
		{
			e.printStackTrace();
		}	
	}
	/** 
  	* Set Buffer size of the internal BufferedReader and BufferedOutputStream 
  	* @param buffSize 
  	*/ 
 	public void setBufferSize (int buffSize) 
 	{ 
  		this.buffSize = buffSize; 
 	}   
}
