package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.AddRecord;
import exception.ConnectionUtilityException;
import exception.DaoInputException;
import exception.DaoOutpuException;
import exception.DataNotFound;
import model.Author;
import model.Book;
import utility.JDBCUtility;

public class AddRecordImpl implements AddRecord {
	public boolean addRecordOfBook(Book book) throws DaoInputException, ConnectionUtilityException {
		boolean result = false;
		Connection con = null;
		// Author author = new Author();
		PreparedStatement ps = null, ps2 = null, ps3 = null;

		try {
			con = JDBCUtility.getConnection();
			ps = con.prepareStatement("insert into book (bookid,title,price) values(?,?,?)");
			ps.setInt(1, book.getId());
			ps.setString(2, book.getTitle());
			ps.setInt(3, book.getPrice());
			int count = ps.executeUpdate();

			ps2 = con.prepareStatement("insert into author (authorid,name) values(?,?)");
			ps3 = con.prepareStatement("insert into lookup (bid,aid) values(?,?)");
		
			for (Author author : book.getAuthors()) {
				ps2.setInt(1, author.getId());
				ps2.setString(2, author.getName());

				ps2.executeUpdate();
			}
			for (Author author : book.getAuthors()) {
				ps3.setInt(1, book.getId());
				ps3.setInt(2, author.getId());
				ps3.executeUpdate();
			
			}

			System.out.println(count);
			if (count > 0) {
				result = true;
			}

		} catch (Exception e) {
			throw new DaoInputException("Data insertion fail.. check wheather ids is unique or not ");
		}

		finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
			}

		}

		return result;
	}

	public List<Book> displayAuthorDao() throws DaoOutpuException  {
		List<Book> list = new ArrayList<Book>();

		Connection con = null;
		// PreparedStatement ps=null;

		try {
			con = JDBCUtility.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from book");
			// ps=con.prepareStatement
			// ResultSet rs=ps.executeQuery();

			while (rs.next()) {
				Book b = new Book();
				int id = rs.getInt("bookid");
				String name = rs.getString("title");
				int price = rs.getInt("price");
				// System.out.println(id+" "+name+" "+job);
//				b.setId(rs.getString(1));
//				b.setTitle(rs.getString(2));
//				b.setPrice(rs.getInt(3));
//		
				b.setId(id);

				b.setPrice(price);

				b.setTitle(name);

				list.add(b);
			}
		} catch (Exception e) {
			throw new DaoOutpuException("Data not available...");
		} finally {
			try {
				System.out.println("connection ids"+con);
				con.close();
				// ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return list;
	}

	public List<Author> displayAuthorDetailsDao() throws DaoOutpuException  {
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<Author> list = new ArrayList<Author>();
		try {
			con = JDBCUtility.getConnection();
			ps = con.prepareStatement("select * from author");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Author author = new Author();
				author.setId(rs.getInt(1));
				author.setName(rs.getString(2));
				
				list.add(author);
			}

		} catch (Exception e) {
			throw new DaoOutpuException("author data not get from table");
		} finally {
			try {
				con.close();
				ps.close();

			} catch (SQLException e) {
			}
		}
		return list;
	}

	public boolean deleteBookDao(Book book) throws DataNotFound, SQLException {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null, ps2, ps3 = null, ps4 = null;
		List<Integer> list = new ArrayList<Integer>();
		try {
			con = JDBCUtility.getConnection();
			//here first take all author id from lookup/relation table to delete record.
			ps3 = con.prepareStatement("select aid from lookup where bid=?");
			ps3.setInt(1, book.getId());
			ResultSet rs = ps3.executeQuery();
			while (rs.next()) {
				int aid1 = rs.getInt("aid");
				//store the author id
				list.add(aid1);
			}
		//delete first data from relation table
			ps = con.prepareStatement("delete from lookup where bid=?");
			ps.setInt(1, book.getId());
			int count = ps.executeUpdate();
			//delete data from book table 
			 ps2 = con.prepareStatement("delete from book where bookid=?");
			 ps2.setInt(1, book.getId());
		     ps2.executeUpdate();
				     
         //delete number of record from the author table
			for (Integer i:list) {
				
				ps4 = con.prepareStatement("delete from author where authorid=?");
				ps4.setInt(1, i);
				ps4.executeUpdate();

			}
			if (count > 0)
				result = true;

		} catch (Exception e) {
			throw new DataNotFound("Record not found exception..");
		} finally {
			con.close();

		}
		return result;
	}

	public Map<Integer, String> getAuthorData() throws DaoOutpuException {
		Map<Integer, String>map=new HashMap<Integer, String>();
		Connection con = null;
		PreparedStatement ps = null;
		//ArrayList<Author> list = new ArrayList<Author>();
		try {
			con = JDBCUtility.getConnection();
			ps = con.prepareStatement("select * from author");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Author author = new Author();
				author.setId(rs.getInt(1));
				author.setName(rs.getString(2));
				map.put(author.getId(), author.getName());
			}
		} catch (Exception e) {
			throw new DaoOutpuException("author data not get from table");
		} finally {
			try {
				con.close();
				ps.close();

			} catch (SQLException e) {
			}
		}
		
		return map;
	}

}


