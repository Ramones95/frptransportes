
package model;

/**
 *
 * @author Ramon Cordeiro
 */
public class Ordem_Servico {
    
        private int id;
        private int cliente;                               // private Cliente cliente;
        private int veiculo;                           //private Veiculo veiculo;
        private String data_partida;                 
        private int endereco_origem;                    //private Endereco_Viagem endereco_origem;
        private int endereco_destino;                   //private Endereco_Viagem endereco_destino;
        private double valor_viagem;
        private int dimensao_pacote;                    // private Pacote dimensao_pacote;
        private double km_percorrido;
        private String nome_cliente;
        private String modelo_veiculo;
        private String marca_veiculo;
        private String nome_motorista;

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getModelo_veiculo() {
        return modelo_veiculo;
    }

    public void setModelo_veiculo(String modelo_veiculo) {
        this.modelo_veiculo = modelo_veiculo;
    }

    public String getMarca_veiculo() {
        return marca_veiculo;
    }

    public void setMarca_veiculo(String marca_veiculo) {
        this.marca_veiculo = marca_veiculo;
    }

    public String getNome_motorista() {
        return nome_motorista;
    }

    public void setNome_motorista(String nome_motorista) {
        this.nome_motorista = nome_motorista;
    }
        

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(int veiculo) {
        this.veiculo = veiculo;
    }

    public String getData_partida() {
        return data_partida;
    }

    public void setData_partida(String data_partida) {
        this.data_partida = data_partida;
    }

    public int getEndereco_origem() {
        return endereco_origem;
    }

    public void setEndereco_origem(int endereco_origem) {
        this.endereco_origem = endereco_origem;
    }

    public int getEndereco_destino() {
        return endereco_destino;
    }

    public void setEndereco_destino(int endereco_destino) {
        this.endereco_destino = endereco_destino;
    }

    public double getValor_viagem() {
        return valor_viagem;
    }

    public void setValor_viagem(double valor_viagem) {
        this.valor_viagem = valor_viagem;
    }

    public int getDimensao_pacote() {
        return dimensao_pacote;
    }

    public void setDimensao_pacote(int dimensao_pacote) {
        this.dimensao_pacote = dimensao_pacote;
    }

    public double getKm_percorrido() {
        return km_percorrido;
    }

    public void setKm_percorrido(double km_percorrido) {
        this.km_percorrido = km_percorrido;
    }
        
        public double calculaViagem(Ordem_Servico os){
            
         double valor = os.getKm_percorrido() * 10;
         
         this.valor_viagem = valor;
         
         
         return valor_viagem;
        }
        

        
                       
        
    
    
    
 
    
    
}
