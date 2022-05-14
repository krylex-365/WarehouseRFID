/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.TagDAO;
import DTO.TagDTO;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class TagBUS {

    TagDAO tagDAO = new TagDAO();

    public ArrayList<TagDTO> getList() {
        return tagDAO.getList();
    }

    public boolean insertTag(TagDTO tagDTO) {
        if (tagDAO.insertTag(tagDTO)) {
            System.out.println("insert success TagBUS");
            return true;
        }
        System.out.println("insert fail TagBUS");
        return false;
    }

    public boolean updateTagsOut(ArrayList<TagDTO> tagDTOs) {
        if (tagDAO.updateTagsOut(tagDTOs)) {
            System.out.println("update success TagBUS");
            return true;
        }
        System.out.println("update fail TagBUS");
        return false;
    }
}
