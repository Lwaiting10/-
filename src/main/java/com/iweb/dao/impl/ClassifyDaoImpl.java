package com.iweb.dao.impl;

import com.iweb.dao.ClassifyDao;
import com.iweb.entity.Classify;
import com.iweb.entity.User;
import com.iweb.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liu Xiong
 * @date 7/12/2023 上午10:28
 */
public class ClassifyDaoImpl implements ClassifyDao {

    @Override
    public void save(Classify classify) {
        String sql = "INSERT INTO classify (name) VALUES (?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, classify.getName());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                classify.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Classify findById(int id) {
        String sql = "SELECT * FROM classify WHERE id = ?";
        Classify classify = null;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                classify = mapResultSetToClassify(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classify;
    }

    @Override
    public Classify findByName(String name) {
        String sql = "SELECT * FROM classify WHERE name = ?";
        Classify classify = null;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                classify = mapResultSetToClassify(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classify;
    }

    @Override
    public List<Classify> findAll() {
        String sql = "SELECT * FROM classify";
        List<Classify> classifyList = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                classifyList.add(mapResultSetToClassify(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classifyList.isEmpty() ? null : classifyList;
    }

    @Override
    public void update(Classify classify) {
        String sql = "UPDATE classify SET name = ? WHERE id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, classify.getName());
            ps.setInt(2, classify.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM classify WHERE id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Classify mapResultSetToClassify(ResultSet resultSet) throws SQLException {
        return new Classify(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }
}
