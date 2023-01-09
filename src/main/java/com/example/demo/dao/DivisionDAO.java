package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.Nullable;

import com.example.demo.model.Division;
import com.example.demo.model.Region;

public class DivisionDAO {
    private Connection connection;


    public DivisionDAO(Connection connection){
        this.connection = connection;
    }

    public List<Division> getAll(){
        List<Division> divisions = new ArrayList<>();
        String query = "SELECT * FROM tb_m_division JOIN tb_m_region ON tb_m_division.region_id = tb_m_region.id;";
        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Division division = new Division();
                Region region = new Region();
                region.setId(resultSet.getInt(3));
                region.setName(resultSet.getString(5));
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
                division.setRegion(region);
                divisions.add(division);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisions;
    }

    public boolean insert(@Nullable Division division){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_m_division (name,region_id) VALUES(?,?)");
            //(id,name) harus sesuai sama yg di tabel region id namenya
            preparedStatement.setString(1, division.getName());
            preparedStatement.setInt(2, division.getRegion().getId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Division division){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tb_m_division SET name = ?,region_id = ? WHERE id = ?");
            preparedStatement.setString(1, division.getName());
            preparedStatement.setInt(2, division.getRegion().getId());
            preparedStatement.setInt(3, division.getId());
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
                Region region = new Region();
                division.setRegion(region);
                region.setId(resultSet.getInt(3));
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return division;
    }
}