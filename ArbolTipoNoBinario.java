public class ArbolTipoNoBinario<E> {
    private Binarytree<E> root;

    public ArbolTipoNoBinario() {
        this.root = null;
    }

    // Método para insertar un paciente en el árbol
    public void insert(Paciente paciente) {
        Node<Paciente> newAssociation = new Node<Paciente>(paciente);
        
        if (root == null) {
            root = new Binarytree<E>(newAssociation);
        } else {
            insertNode(root, newAssociation);
        }
    }
    
    // Método privado para insertar un nodo en el árbol
    private Binarytree<E> insertNode(Binarytree<E> current, Node<Paciente> association) {
        if (current == null) {
            return new Binarytree<E>(association);
        }
    
        if (association.getKey().getPrioridad().compareTo(current.association.getKey().getPrioridad()) > 0) {
            current.left = insertNode(current.left, association);
        } else if (association.getKey().getPrioridad().compareTo(current.association.getKey().getPrioridad()) < 0) {
           current.right = insertNode(current.right, association);
        } else {
            current.right = insertNode(current.right, association);
        }
        
        return current;
    }

    // Método para eliminar la raíz y reemplazarla con el siguiente paciente
    public void eliminarRaizYReemplazar() {
        if (root == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        System.out.println("Cliente " + root.association.getKey().getNombre() + " fue atendido. Pase al siguiente:");

        if (root.left == null && root.right == null) {
            root = null; // Si la raíz no tiene hijos, simplemente la eliminamos
        } else if (root.right != null) {
            Binarytree<E> siguiente = encontrarMasDerecha(root); // Buscamos el nodo más a la izquierda del subárbol derecho
            eliminarRaizYReemplazar(root); // Llamada recursiva para eliminar el siguiente nodo en el subárbol derecho
        } else {
            Binarytree<E> siguiente = encontrarMasIzquierda(root); // Buscamos el nodo más a la izquierda del subárbol izquierdo
            eliminarRaizYReemplazar(root); // Llamada recursiva para eliminar el siguiente nodo en el subárbol izquierdo
        }
    }

    // Método para encontrar el nodo más a la izquierda
    public Binarytree<E> encontrarMasIzquierda(Binarytree<E> current) {
        if (current.left != null) {
            current.left = encontrarMasIzquierda(current.left); // Buscamos el nodo más a la izquierda en el subárbol izquierdo
            return current;
        } else {
            root.association = current.association; // Reemplazamos la raíz con el nodo encontrado más a la izquierda
            return null; // Dejamos esa hoja como nula
        }
    }

    // Método para encontrar el nodo más a la derecha
    public Binarytree<E> encontrarMasDerecha(Binarytree<E> current) {
        if (current.right != null) {
            current.right = encontrarMasDerecha(current.right); // Buscamos el nodo más a la derecha en el subárbol derecho
            return current;
        } else {
            root.association = current.association; // Reemplazamos la raíz con el nodo encontrado más a la derecha
            return null; // Dejamos esa hoja como nula
        }
    }

    // Método privado para eliminar la raíz y reemplazarla con el siguiente nodo
    private void eliminarRaizYReemplazar(Binarytree<E> current) {
        if (current.right == null) {
            if (current.left == null) {
                // No hacemos nada, ya que la raíz no tiene hijos
            } else if (current.association.getKey().getPrioridad().compareTo(current.left.association.getKey().getPrioridad()) > 0) {
                Node<Paciente> temp = null;
                temp = current.association;
                current.association = current.left.association;
                current.left.association = temp;
                eliminarRaizYReemplazar(current.left);
            } else {
                // No hacemos nada, ya que la prioridad del siguiente paciente no es mayor que la de la raíz
            }
        } else if (current.association.getKey().getPrioridad().compareTo(current.right.association.getKey().getPrioridad()) > 0) {
            Node<Paciente> temp = null;
            temp = current.association;
            current.association = current.right.association;
            current.right.association = temp;
            eliminarRaizYReemplazar(current.right);
        } else {
            // No hacemos nada, ya que la prioridad del siguiente paciente no es mayor que la de la raíz
        }
    }

    // Método para recorrer el árbol en orden
    private void inOrder(Binarytree<E> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println("(" + node.association.getKey().getNombre() + " " + node.association.getKey().getPrioridad() + ")");
            inOrder(node.right);
        }
    }
}
