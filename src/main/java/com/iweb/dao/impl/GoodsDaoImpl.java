package com.iweb.dao.impl;

import com.iweb.dao.GoodsDao;
import com.iweb.entity.Goods;
import com.iweb.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liu Xiong
 * @date 7/12/2023 上午10:40
 */
public class GoodsDaoImpl implements GoodsDao {
    @Override
    public void save(Goods goods) {
        String sql = "INSERT INTO goods (cid, name, price, stock, info) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, goods.getCid());
            ps.setString(2, goods.getName());
            ps.setBigDecimal(3, goods.getPrice());
            ps.setInt(4, goods.getStock());
            ps.setString(5, goods.getInfo());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                goods.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Goods findById(int id) {
        String sql = "SELECT * FROM goods WHERE id = ?";
        Goods goods = null;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                goods = mapResultSetToGoods(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return goods;
    }

    @Override
    public Goods findByName(String name) {
        String sql = "SELECT * FROM goods WHERE name = ?";
        Goods goods = null;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                goods = mapResultSetToGoods(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return goods;
    }

    @Override
    public List<Goods> findAll() {
        String sql = "SELECT * FROM goods";
        List<Goods> goods = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                goods.add(mapResultSetToGoods(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return goods.isEmpty() ? null : goods;
    }

    @Override
    public void update(Goods goods) {
        String sql = "UPDATE goods SET cid = ?, name = ?, price = ?, stock = ?, info = ? WHERE id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, goods.getCid());
            ps.setString(2, goods.getName());
            ps.setBigDecimal(3, goods.getPrice());
            ps.setInt(4, goods.getStock());
            ps.setString(5, goods.getInfo());
            ps.setInt(6, goods.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM goods WHERE id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Goods> findByClassifyId(int classifyId) {
        String sql = "SELECT * FROM goods WHERE cid = ?";
        List<Goods> goods = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, classifyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                goods.add(mapResultSetToGoods(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return goods.isEmpty() ? null : goods;
    }

    private Goods mapResultSetToGoods(ResultSet resultSet) throws SQLException {
        return new Goods(
                resultSet.getInt("id"),
                resultSet.getInt("cid"),
                resultSet.getString("name"),
                resultSet.getBigDecimal("price"),
                resultSet.getInt("stock"),
                resultSet.getString("info")
        );
    }
}
