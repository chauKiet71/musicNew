package Table;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import utils.XJdbcc;

public class tableDAO {

    String sql_selectAll = "Select * from BaiHat";
    String sql_selectById = "Select MaBh from BaiHat where tenBh = ?";

    public List<Model_Table> selectAll() {
        return this.selectBySql(sql_selectAll);
    }

    public Model_Table selectById(String id) {
        List<Model_Table> list = this.selectBySql(sql_selectById, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    protected List<Model_Table> selectBySql(String sql, Object... args) {
        List<Model_Table> list = new ArrayList<Model_Table>();
        try {
            ResultSet rs = XJdbcc.query(sql, args);
            while (rs.next()) {
                Model_Table entity = new Model_Table();

                entity.setIcon(new ImageIcon(getClass().getResource(rs.getString(6))));
                entity.setName(rs.getString(2));
                entity.setSing(rs.getString(3));
                entity.setView(rs.getInt(10));
                entity.setTime(rs.getString(8));
                entity.setMaBh(rs.getString(1));
                list.add(entity);

            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
