package org.miguelhalys.test.hazelcast.cache;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.hazelcast.query.Predicates;

import org.miguelhalys.test.hazelcast.server.HazelcastTestBase;
import com.miguelhalys.hazelcast.domain.Employee;

public class HazelcastCacheTest extends HazelcastTestBase {
	
	private static final String MAP_EMPLOYEE_NAME = "employee";
	private static Employee[] testData = 
			new Employee[]{
					new Employee("Carlos", 24, true , 23.0),
					new Employee("Joao", 26, true, 30.0),
					new Employee("Pepe", 29, false , 0.6),
					new Employee("Braulio", 32, true , 72.0),
					new Employee("Jonás", 43, true, 45.0),
					new Employee("Joaquín", 61, true , 65.0)
					};
	
	private static HazelcastInstance hazelcastInstance;
	
	@BeforeClass
	public static void setUp(){
		hazelcastInstance = Hazelcast.newHazelcastInstance();
		prepareTestData();
	}
	
	@Test
	public void shouldGetAllLT30AndActiveEmployees(){
		__GIVEN("A HZ Map");
		IMap<String, Employee> map = hazelcastInstance.getMap( MAP_EMPLOYEE_NAME );

		
		__WHEN("SELECT * from employee WHERE active=TRUE AND age<30");
		EntryObject e = new PredicateBuilder().getEntryObject();
		Predicate<?, ?> predicate = e.is( "active" ).and( e.get( "age" ).lessThan( 30 ) );
		
		Collection<Employee> employees = map.values( predicate );

		__THEN("The resulting collection must be 2");
		assertEquals(2, employees.size());
		employees.forEach(employee -> System.out.println(employee));
		
	}

	/**
	 * More on predicates:
	 * https://searchcode.com/codesearch/view/51556953/
	 * */
	@Test
	public void shouldGetAllEmployeesWhoseNameStartsWithJo(){
		__GIVEN("A HZ Map");
		IMap<String, Employee> map = hazelcastInstance.getMap( MAP_EMPLOYEE_NAME );

		
		__WHEN("SELECT * from employee WHERE name LIKE 'Jo'");
		Predicate<?, ?> predicate = Predicates.like("name", "Jo%");
		Collection<Employee> employees = map.values( predicate );

		__THEN("The resulting collection must be 3");
		assertEquals(3, employees.size());
		employees.forEach(employee -> System.out.println(employee));
		
	}
	
	
	private static void prepareTestData(){
		final IMap<String, Employee> employeeMap = hazelcastInstance.getMap(MAP_EMPLOYEE_NAME);
		System.out.println("Loaded data: ");
		
		Arrays.stream(testData).forEach( a -> {
			employeeMap.put(a.getName(),  a);
			System.out.println(a);
		});

			
			
	}

}
