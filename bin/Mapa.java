/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unc.syso.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author mc
 */
public class MapaImg extends HashMap<String, byte[]>{
    
    public static MapaImg creo() throws SQLException, IOException{
        return new MapaImg();
    }
    private MapaImg() throws SQLException, IOException{
        String sel="select id_pictograma, imagenp from sga_pictograma where id_pictograma not in (11,12)";
        cargo(sel);
        sel="select grafico, imagen from sga_pictograma where id_pictograma in (11,12)";
        cargo(sel);
    }
    
    private void cargo(String select) throws SQLException, IOException{
//        Logger.getLogger(MapaImg.class.getName()).log(Level.INFO, select);

        Statement stm = ConexionJNDI.getStmt();
        ResultSet rs = stm.executeQuery(select);
        rs.next();
//        Logger.getLogger(MapaImg.class.getName()).log(Level.INFO, "despues de next");
        for (;!rs.isAfterLast();){
            String ke = rs.getObject(1).toString();
            InputStream fi = rs.getBlob(2).getBinaryStream();
            byte[] bb = new byte[fi.available()];
            fi.read(bb);
            this.put(ke, bb);
            rs.next();
        }
        
    
    }
}
