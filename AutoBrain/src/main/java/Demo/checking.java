package Demo;

public class checking 
{
	
static Xls_Reader reader;
	
	public static void main(String[] args) throws Exception 
	{
		reader = new Xls_Reader("C:\\Users\\Rajesh\\Desktop\\test.xlsx");
		String ck = reader.getData(0, 1, 2);
		System.out.println(ck);
	}
}
