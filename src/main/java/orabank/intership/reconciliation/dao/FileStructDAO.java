package orabank.intership.reconciliation.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import orabank.intership.reconciliation.models.Colonne;
import orabank.intership.reconciliation.models.FileStruct;
import orabank.intership.reconciliation.models.InternalDataStruct;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileStructDAO {
    private Integer id;
    private Integer numberOfColumns;
    private List<Colonne> colonnes;

    public static FileStructDAO fromEntity(FileStruct fileStruct){
        if(fileStruct==null){
            return null;
        }
        return FileStructDAO.builder()
                .id(fileStruct.getId())
                .numberOfColumns(fileStruct.getNumberOfColumns())
                .colonnes(fileStruct.getColonnes())
                .build();
    }

    public static FileStruct toEntity(FileStructDAO fileStructDAO){
        if(fileStructDAO==null){
            return null;
        }
        FileStruct fileStruct= new FileStruct();
        fileStruct.setId(fileStructDAO.getId());
        fileStruct.setNumberOfColumns(fileStruct.getNumberOfColumns());
        fileStruct.setColonnes(fileStructDAO.getColonnes());

        return fileStruct;
    }




}
