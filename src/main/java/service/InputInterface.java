package service;

import exception.DaoInputException;
import exception.DaoOutpuException;
import exception.DataNotFound;
import exception.InpuFormatEcxeption;

public interface InputInterface {
	void addMethod() throws  DaoInputException, InpuFormatEcxeption;

	void displayAuthorDetails() throws  DaoOutpuException;

	void displayAuthor() throws DaoOutpuException;

	void deleteBookByBookId()throws DataNotFound;

	void displyAllRecord() throws  DaoOutpuException;



	



}


