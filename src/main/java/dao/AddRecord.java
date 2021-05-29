package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import exception.ConnectionUtilityException;
import exception.DaoInputException;
import exception.DaoOutpuException;
import exception.DataNotFound;
import model.Author;
import model.Book;

public interface AddRecord {
	boolean addRecordOfBook(Book book)throws DaoInputException,ConnectionUtilityException;

	List<Book> displayAuthorDao() throws  DaoOutpuException;

	List<Author> displayAuthorDetailsDao()throws DaoOutpuException;

	boolean deleteBookDao(Book book)throws DataNotFound,SQLException;

	Map<Integer, String> getAuthorData()throws  DaoOutpuException ;


	}
	


