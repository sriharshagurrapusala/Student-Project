import java.sql.*;

class Student
{
	int rollno;
	String name;
}

class StudentDAO
{
	Connection con=null;
	
	public  void connect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","Password@1729");
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public Student getStudent(int rollno)
	{
		Student s=new Student();
		s.rollno=rollno;
		String query="select sname from college.student where srollno="+rollno;
		try
		{

			try(Statement st=con.createStatement();)
			{
				ResultSet rs=st.executeQuery(query);
				if(rs.next())
				{
				String name=rs.getString(1);
				s.name=name;
				}
				
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return s;
	}
	
	public void addStudent(int rollno,String name)
	{
		Student s=new Student();
		s.rollno=rollno;
		s.name=name;
		String query="insert into college.student(srollno,sname) values (?,?)";
		try {
			try(PreparedStatement pst=con.prepareStatement(query);)
			{
				pst.setInt(1, rollno);
				pst.setString(2, name);
				int count=pst.executeUpdate();
				System.out.println(count+" row inserted");
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void updateStudent(int rollno,int newRollno,String newName)
	{
		Student s=new Student();
		String query="update college.student set srollno=?,sname=? where srollno="+rollno;
		try {
			try(PreparedStatement pst=con.prepareStatement(query);)
			{
				pst.setInt(1, newRollno);
				pst.setString(2, newName);
				int count=pst.executeUpdate();
				System.out.println(count+"rows updated");
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void deleteStudent(int rollno)
	{
		String query="delete from college.student where srollno="+rollno;
		try {
			try(Statement st=con.createStatement();)
			{
				int count=st.executeUpdate(query);
				System.out.println(count+"rows deleted");
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
public class StudentProject {
	public static void main(String[] args)
	{
		StudentDAO dao=new StudentDAO();
		int rollno=12;
		dao.connect();
		dao.deleteStudent(rollno);
		
	}
}
