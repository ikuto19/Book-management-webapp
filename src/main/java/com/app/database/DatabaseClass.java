package com.app.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.entity.ISBN;
import com.app.entity.LoginUser;
import com.app.repository.ISBNRepository;

public class DatabaseClass {
	private List<ISBN> manageBookDB;
	
	public DatabaseClass(List<ISBN> manageBookDB) {
		this.manageBookDB = manageBookDB;
	}

	public String registDB(ISBN isbnInfo, ISBNRepository isbnRepository) throws SQLException {
		String message = null;
		
		if(isbnInfo.getTitle().equals("なし") || isbnInfo.getPublisher().equals("なし") || isbnInfo.getAuthor().equals("なし")){
			message = "書籍情報を取得できなかったので、登録できませんでした.";
		}else if(convertStrings(manageBookDB).contains(isbnInfo.getIsbn())) {
			message = "すでにデータベースに存在します.";
		} else {
			message = "新しくデータベースに登録しました.";
			isbnRepository.save(isbnInfo);
		}
		return message;
	}

	public String deleteDB(ISBN isbnInfo, ISBNRepository isbnRepository) throws SQLException {
		String message = null;
		
		if(convertStrings(manageBookDB).contains(isbnInfo.getIsbn())) {
			message = "データベースから削除しました.";
			isbnRepository.delete(isbnInfo);
		}else {
			message = "データベースに存在しません.";
		}
		return message;
	}

	public List<ISBN> checktDB() {
		return manageBookDB;
	}
	
	public List<String> convertStrings(List<ISBN> manageBook){
		List<String> isbnList = new ArrayList<String>();
		for(ISBN isbn:manageBook) {
			isbnList.add(isbn.getIsbn());
		}
		return isbnList;
	}

}