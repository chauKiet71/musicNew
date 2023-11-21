/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.*;
import utils.XJdbcc;

/**
 *
 * @author PC
 */
public class TaiKhoanDAO extends MusicDAO<TaiKhoan, String> {

    String INSERT = "INSERT INTO TaiKhoan (TenTK, MatKhau, VaiTro, avatar, TrangThai) VALUES (?, ?, ?, ?, ?)";
    String UPDATE = "UPDATE TaiKhoan set avatar = ?  WHERE TenTK = ?";
    String DELETE = "DELETE FROM TaiKhoan WHERE TenTK = ?";
    String SELECT_BY_ID = "SELECT * FROM TaiKhoan WHERE TenTK = ?";
    
    String SELECT_ALL = "SELECT * FROM TaiKhoan";

    @Override
    public void insert(TaiKhoan entity) {
        try {
            XJdbcc.update(INSERT, entity.getTenTk(), entity.getMatKhau(), entity.getAvatar(), entity.isVaiTro(), entity.getTrangThai());
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(TaiKhoan entity) {
        try {
            XJdbcc.update(UPDATE, entity.getAvatar(),   entity.getTenTk());
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String Key) {
        try {
            XJdbcc.update(DELETE, Key);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public TaiKhoan selectById(String Key) {
        List<TaiKhoan> list = this.selectBySql(SELECT_BY_ID, Key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<TaiKhoan> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

    @Override
    protected List<TaiKhoan> selectBySql(String sql, Object... args) {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbcc.query(sql, args);
            while (rs.next()) {                
                TaiKhoan tk = new TaiKhoan();
                tk.setTenTk(rs.getString(1));
                tk.setMatKhau(rs.getString(2));
                tk.setAvatar(rs.getString(3));
                tk.setVaiTro(rs.getBoolean(4));
                tk.setTrangThai(rs.getString(5));
                list.add(tk);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
