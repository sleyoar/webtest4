package ajax.dao;

import ajax.entity.Stu;

import java.util.List;

public interface IStuDao {
    int saveStu(Stu stu);
    int deleteStu(int id);
    int updateStu(Stu stu);
    List<Stu> getAllStus();
    Stu findbyId(int id);
}
