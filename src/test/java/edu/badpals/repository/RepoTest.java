package edu.badpals.repository;
import edu.badpals.ExamenAccesoADatos.ServiceItem;
import edu.badpals.ExamenAccesoADatos.domain.MagicalItem;
import edu.badpals.ExamenAccesoADatos.domain.Order;
import edu.badpals.ExamenAccesoADatos.domain.Wizard;
import edu.badpals.ExamenAccesoADatos.repository.Repositorio;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

@QuarkusTest
public class RepoTest {

    @PersistenceContext
	EntityManager em;
/**
	@Inject
	Repositorio repo;
	/**
	@Inject
	ServiceItem servicio;

    /**
	 * Tests sobre los mappings
	 * 
	 * Observa el esquema de la base de datos 
	 * en el fichero:
	 * src/main/resources/schema.sql
	 */
	
	/**
     * Completa la definicion y el mapping
     * de la clase MagicalItem a la tabla t_items.
     * El id de esta clase ha de seguir una estrategia Identity
     */


	@Test
	public void test_mapping_MagicalItem() {
		MagicalItem elixir = em.find(MagicalItem.class, 2L);
		Assertions.assertThat(elixir).isNotNull();
		Assertions.assertThat(elixir.toString()).containsIgnoringCase("Elixir of the Mongoose"); // item_name
		Assertions.assertThat(elixir.toString()).contains("7"); // item_quality
		Assertions.assertThat(elixir.toString()).contains("MagicalItem"); // item_type
		Assertions.assertThat(elixir.getId()).isEqualTo(2L);
	}
    
	/** 
	 * Completa la definicion y el mapping
	 * de la clase Wizards a la tabla t_wizards
	 * 
	 * Los Wizards tienen una propiedad "person" de
	 * un tipo enumerado con los valores:
	 * MUGGLE, SQUIB, NOMAJ, MUDBLOOD
	 * 
	 * La anotacion jakarta.persistence para mapear 
	 * a una tabla una propiedad Enum es
	 * 	@Enumerated(EnumType.STRING)
	 */

	@Test
	public void test_mapping_wizard() {
		Wizard squib = em.find(Wizard.class, "Marius Black");
		Assertions.assertThat(squib).isNotNull();
		Assertions.assertThat(squib.toString()).contains("Marius Black");
		Assertions.assertThat(squib.toString()).contains("15"); //wizard_dexterity
		Assertions.assertThat(squib.toString()).contains("SQUIB");  //tipo enumerado
	}

	/**
	 * Completa la definicion y el mapping
	 * de la clase Order a la tabla t_orders
	 * El id de esta clase ha de seguir una estrategia
	 * Identity
	 */

	@Test
	public void test_mapping_order() {
		Order pedido = em.find(Order.class, 1L);
		Assertions.assertThat(pedido).isNotNull();
		Assertions.assertThat(pedido.toString()).contains("Marius Black"); //ord_wizard
		Assertions.assertThat(pedido.toString()).containsIgnoringCase("Elixir of the Mongoose"); //ord_item
	}

	/**
	 * Crea una clase llamada Repositorio
	 * e inyectala en los casos test
	 * (ha de ser un bean) 
	 * 
	 * Utiliza el código:
	 * @Inject
	 * Repositorio repo;
	 */
	/**
	@Test
	public void test_repositorio_existe() {
		Assertions.assertThat(repo).isNotNull();
	}

	/**
	 * Implementa el metodo loadWizard del repositorio
	 * que devuelve un Optional del mago/a con el nombre indicado
	 */
	/**
	@Test
	public void test_load_wizard() {
		Assertions.assertThat(repo).isNotNull();
		Wizard squib = repo.loadWizard("Hermione").get();
		Assertions.assertThat(squib).isNotNull();
		Assertions.assertThat(squib.toString()).contains("Hermione");
		Assertions.assertThat(squib.toString()).contains("100");
		Assertions.assertThat(squib.toString()).contains("MUDBLOOD");

		// test no existe el mago
		Assertions.assertThat(repo.loadWizard("Severus Snape")).isEmpty();
	}

	/**
	 * Implementa el metodo loadItem() del repositorio
	 * que devuelve un Optional del Item con el nombre indicado.
	 * 
	 * Ojo que el nombre del item no es la clave primaria.
	 * 
	 * El metodo devueve el primer item cuyo nombre
	 * coincida con el especificado.
	 */
	/**
	@Test
	public void test_load_item() {
		Assertions.assertThat(repo).isNotNull();
		MagicalItem item = repo.loadItem("Aged Brie").get();
		Assertions.assertThat(item).isNotNull();
		Assertions.assertThat(item.getName()).isEqualTo("Aged Brie");
		Assertions.assertThat(item.getQuality()).isEqualTo(10);

		// test no existe el item
		Assertions.assertThat(repo.loadItem("Varita de Sauco")).isEmpty();
	}

	/**
	 * Implementa el metodo loadItem(name, quality, type) 
	 * del repositorio que devuelve un Optional del Item
	 * con el nombre indicado.
	 *  
	 * El item devuelto ha de tener el mismo 
     * name, quality y type que el de la peticion
     * y no cualquier otro item de la base de datos
     * que tenga sólo el mismo nombre.
	 */
	/**
	@Test
	public void test_load_item_equal() {
		Assertions.assertThat(repo).isNotNull();
		MagicalItem brie = new MagicalItem("Aged Brie", 0, "MagicalItem");
		MagicalItem item = repo.loadItem(brie).get();
		Assertions.assertThat(item).isNotNull();
		Assertions.assertThat(item.getName()).isEqualTo("Aged Brie");
		Assertions.assertThat(item.getQuality()).isZero();

		// test no existe el item
		brie = new MagicalItem("Aged Brie", 1000, "MagicalItem");
		Assertions.assertThat(repo.loadItem(brie)).isEmpty();
	}

	/**
	 * Implementa el metodo loadItems() del repositorio
	 * que devuelve una lista de Items 
	 * con el nombre indicado
	 * 
	 * Ojo que el nombre del item no es la clave primaria.
	 */
	/**
	@Test
	public void test_load_items() {
		Assertions.assertThat(repo).isNotNull();

		List<MagicalItem> items = repo.loadItems("Aged Brie");
		Assertions.assertThat(items).isNotEmpty().hasSize(2);
		Assertions.assertThat(items.get(0)).hasFieldOrPropertyWithValue("name", "Aged Brie");
		Assertions.assertThat(items.get(1)).hasFieldOrPropertyWithValue("quality", 0);

		// test no existe el item
		Assertions.assertThat(repo.loadItems("Varita de Sauco")).isEmpty();
	}


	/**
	 * Implementa el metodo placeOrder(wizard, item) del repositorio 
	 * que genera un pedido de un item para un mago determinado.
	 * El pedido se guarda en la base de datos.
	 *  
	 * Los magos/as mudblood NO pueden comprar un item.
	 */
	/**
	@Test
	@Transactional
	public void test_pedido() {
 
		// Hermione no puede comprar items
		Assertions.assertThat(repo).isNotNull();
		Optional<Order> orden = repo.placeOrder("Hermione", "Elixir of the Mongoose");
		Assertions.assertThat(orden).isEmpty();
 
		// Marius Black compra un item
		orden = repo.placeOrder("Marius Black", "Elixir of the Mongoose");
		Assertions.assertThat(orden).isNotEmpty();
 
		Assertions.assertThat(orden.get().getId()).isNotZero();
		Assertions.assertThat(orden.get().getWizard().getName()).isEqualTo("Marius Black");
		Assertions.assertThat(orden.get().getItem().getName()).isEqualTo("Elixir of the Mongoose");
  
		// query para obtener todos los pedidos de Marius
		// de manera agnostica al patron DAO /Active record
		TypedQuery<Order> query = em.createQuery("select orden from Order orden join orden.wizard wizard where wizard.name = 'Marius Black'", Order.class);
		List<Order> pedidos = query.getResultList();
		  
		Assertions.assertThat(pedidos).isNotNull().hasSize(3);
		Assertions.assertThat(pedidos.get(2).getWizard().getName()).isEqualTo("Marius Black");
		Assertions.assertThat(pedidos.get(2).getItem().getName()).isEqualToIgnoringCase("Elixir of the Mongoose");
	}
 
	/**
	 * Implementa el metodo createItem() del repositorio
	 * que crea un item en la base de datos.
	 */
	/**
	@Test
	@Transactional
	public void test_create_item() {
		Assertions.assertThat(repo).isNotNull();

		repo.createItem("Guardapelo", 100, "MagicalItem");

		MagicalItem relic = repo.loadItem("Guardapelo").get();
		Assertions.assertThat(relic).isNotNull();
		Assertions.assertThat(relic.getName()).isEqualTo("Guardapelo");
		Assertions.assertThat(relic.getQuality()).isEqualTo(100);
		Assertions.assertThat(relic.getType()).isEqualTo("MagicalItem");
	}

	/**
	 * Implementa el metodo createItems() del repositorio
	 * que crea los items indicados en la base de datos.
	 * 
	 * Asegurate de que el metodo loadItem() anterior
	 * devueve el primer item cuyo nombre
	 * coincida con el especificado, sino, tu codigo
	 * devolvera uno de los pases a backstage que no
	 * es el que buscamos.
	 */
	/**
	@Test
	@Transactional
	public void test_create_items() {
		Assertions.assertThat(repo).isNotNull();

		List<MagicalItem> items = List.of(
			new MagicalItem("Sulfuras, Hand of Ragnaros", 0, "MagicalItem"),
			new MagicalItem("Sulfuras, Hand of Ragnaros", -1, "MagicalItem"),
			new MagicalItem("Backstage passes to a TAFKAL80ETC concert", 15, "MagicalItem"),
			new MagicalItem("Backstage passes to a TAFKAL80ETC concert", 10, "MagicalItem"),
			new MagicalItem("Backstage passes to a TAFKAL80ETC concert", 5, "MagicalItem"));
		
		repo.createItems(items);

		// los pases se han guardado
		MagicalItem backstage = repo.loadItem("Backstage passes to a TAFKAL80ETC concert").get();
		Assertions.assertThat(backstage).isNotNull();
		Assertions.assertThat(backstage.getName()).isEqualTo("Backstage passes to a TAFKAL80ETC concert");
		Assertions.assertThat(backstage.getQuality()).isEqualTo(15);
		Assertions.assertThat(backstage.getType()).isEqualTo("MagicalItem");

		// los sulfuras se han guardado
		List<MagicalItem> manos = repo.loadItems("Sulfuras, Hand of Ragnaros");
		Assertions.assertThat(manos).isNotNull().isNotEmpty().hasSize(2);
		Assertions.assertThat(items.get(0)).hasFieldOrPropertyWithValue("name", "Sulfuras, Hand of Ragnaros");
		Assertions.assertThat(items.get(1)).hasFieldOrPropertyWithValue("quality", -1);
	}

	/**
     * Implementa el metodo deleteItem() del repositorio
	 * que elimina el item indicado en la base de datos.
     * Los parametros necesarios son:
     *      "name" con el nombre del item
	 *      "quality" con la calidad del item
	 *      "type" con el tipo de item.
     * 
     * El item eliminado ha de tener el mismo 
     * name, quality y type que el de la peticion
     * y no cualquier otro item de la base de datos
     * solo con el mismo nombre.
     */
	/**
	@Test
	@Transactional
	public void test_delete_item() {
		Assertions.assertThat(repo).isNotNull();

		// Item eliminado porque no existe en orders => no rompe integridad referencial
		MagicalItem item = new MagicalItem("Time-Turner", 200, "MagicalItem");
		repo.deleteItem(item);

		MagicalItem travel = em.find(MagicalItem.class, 7L);
		Assertions.assertThat(travel).isNull();

		// Si no existe el item
		item = new MagicalItem("Varita de Sauco", 1000, "MagicalItem");
		Assertions.assertThat(repo.loadItem("Varita de Sauco")).isEmpty();
	}

	/**
	 * Implementa un servicio, 
	 * indica que es un bean
	 * e inyectalo en los casos test
	 * 
	 * Utiliza el código:
	 * @Inject
	 * ServiceItem servicio;
	 */
	/**
	@Test
	public void test_servicio_existe() {
		Assertions.assertThat(servicio).isNotNull();
	}

	/**
	 * Recuerda inyectar el repositorio en el servicio
	 * y continua completando los test de ResourceTest.
	 */

}
