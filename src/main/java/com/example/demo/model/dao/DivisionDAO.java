package com.example.demo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.Nullable;

import com.example.demo.model.Division;

public class DivisionDAO {
    private Connection connection;


    public DivisionDAO(Connection connection){
        this.connection = connection;
    }

    public List<Division> getAll(){
        List<Division> divisions = new ArrayList<>();
        String query = "SELECT * FROM tb_m_division";
        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Division division = new Division();
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
                divisions.add(division);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisions;
    }

    public boolean insert(@Nullable Division division){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_m_division (name) VALUES(?)");
            //(id,name) harus sesuai sama yg di tabel region id namenya
            preparedStatement.setString(1, division.getName());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Division division){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tb_m_division SET name = ? WHERE id = ?");
            preparedStatement.setString(1, division.getName());
            preparedStatement.setInt(2, division.getId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return false;
    }

    public boolean delete(Integer id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tb_m_division WHERE id = ?");
            preparedStatement.setInt(1, id);
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Division getById(Integer id){
        String query = "SELECT * FROM tb_m_division WHERE id = ?";
        Division division = new Division();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return division;
    }
}