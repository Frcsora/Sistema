package sistema;

    public class Usuario {
        private String nombre;
        
        Usuario(){  
        }
        Usuario(String nombre){
            this.nombre = nombre;
        }
        public String getNombre(){
            return nombre;
        }
        public void setNombre(String nombre){
            this.nombre = nombre;
        }
    }
