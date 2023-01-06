package com.example.demo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.Nullable;

import com.example.demo.model.Region;


public class RegionDAO {
    private Connection connection;
   
    public RegionDAO(Connection connection){
        this.connection = connection;
    }

    public List<Region> getAll(){
        List<Region> regions = new ArrayList<>();
        String query = "SELECT * FROM tb_m_region";
        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Region region = new Region();
                region.setId(resultSet.getInt(1));
                region.setName(resultSet.getString(2));
                regions.add(region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regions;
    }

    public boolean insert(@Nullable Region region){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_m_region (name) VALUES(?)");
            //(id,name) harus sesuai sama yg di tabel region id namenya
            preparedStatement.setString(1, region.getName());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Region region){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tb_m_region SET name = ? WHERE id = ?");
            preparedStatement.setString(1, region.getName());
            preparedStatement.setInt(2, region.getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tb_m_region WHERE id = ?");
            preparedStatement.setInt(1, id);
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Region getById(Integer id){
        String query = "SELECT * FROM tb_m_region WHERE id = ?";
        Region region = new Region();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                region.setId(resultSet.getInt(1));
                region.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }
}
