package dao_interface;

import model.TinTuc;

import java.util.List;

public interface I_DAO_TinTuc {

    List<TinTuc> getAllTinTuc();
    TinTuc getTinTucById(String id);
    boolean addTinTuc(TinTuc tinTuc);
    boolean updateTinTuc(TinTuc tinTuc);
    boolean deleteTinTuc(String id);

}
