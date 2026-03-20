import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<ProdutosDTO> lista = new ArrayList<>();

    // CADASTRAR PRODUTO
    public void cadastrarProduto(ProdutosDTO produto){

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {

            conn = new conectaDAO().connectDB();

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, produto.getNome());
            pstm.setInt(2, produto.getValor());
            pstm.setString(3, produto.getStatus());

            pstm.execute();
            pstm.close();

        } catch (Exception e) {

            System.out.println("Erro ao cadastrar: " + e.getMessage());

        }
    }


    // LISTAR PRODUTOS
    public ArrayList<ProdutosDTO> listarProdutos(){

        String sql = "SELECT * FROM produtos";

        try {

            conn = new conectaDAO().connectDB();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while(rs.next()){

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                lista.add(produto);

            }

        } catch (Exception e) {

            System.out.println("Erro ao listar: " + e.getMessage());

        }

        return lista;
    }


    // VENDER PRODUTO
    public void venderProduto(int id){

        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try {

            conn = new conectaDAO().connectDB();

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();

            pstm.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Erro ao vender produto: " + e.getMessage());

        }
    }


    // LISTAR PRODUTOS VENDIDOS
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){

        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        ArrayList<ProdutosDTO> listaVendidos = new ArrayList<>();

        try {

            conn = new conectaDAO().connectDB();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while(rs.next()){

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listaVendidos.add(produto);

            }

        } catch (Exception e) {

            System.out.println("Erro ao listar vendidos: " + e.getMessage());

        }

        return listaVendidos;
    }
}