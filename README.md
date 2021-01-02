# Project Management Application

This project was created in Spring Tool Suite IDE using Spring version 5 & Spring Boot 2.4.1.
Also used Thymeleaf to design the html pages & bind data from Spring.
Integrated different profiles, Test profile runs on H2 embedded db & Prod profile runs on PostgreSql, using CRUDRepository to store & fetch data from the database.
Added Aspect to log around methods. Added Spring Data Rest to fetch JSON body and use API calls to manipulate data.
Added Pie Chart to display project status, also added timeline to display the project timeline.


## Development server
Run the app using dev server. Navigate to `http://localhost:8080/`. 

## Build

*	Didn't use CascadeType.ALL as employees should not get deleted if a project is deleted
		FetchType.Lazy is used to load the employees only on demand
		@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
				fetch = FetchType.LAZY)
		@JoinTable(name="project_employee", joinColumns = @JoinColumn(name="employee_id"), inverseJoinColumns = @JoinColumn(name="project_id"))
		@JsonIgnore
		private List<Project> projects;
		
*	http.authorizeRequests()
		//antMatchers works as priority as the are written in the method
		.antMatchers("/projects/new").hasRole("ADMIN")
		.antMatchers("/projects/save").hasRole("ADMIN")
		.antMatchers("/employees/new").hasRole("ADMIN")
		.antMatchers("/employees/save").hasRole("ADMIN")
		//all users can access the H2 db
		//.antMatchers("/h2-console/**").permitAll()
		.antMatchers("/", "/**").permitAll()
		//load the page if exists
		//.and().formLogin().loginPage("/login-page")
		.and().formLogin();
	
	//to enable other than GET HTTP verbs
	http.csrf().disable();
	
*	In Memory authentication of the user
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("myuser").password("pass").roles("USER")
				.and()
				.withUser("dhiraj").password("abcd").roles("USER")
				.and()
				.withUser("iamdrjsolanki").password("pass").roles("USER")
				.and()
				.withUser("manager").password("manager").roles("ADMIN");
		}
	
*	JDBC authentication of the user
	Automatically creates USERS & AUTHORITIES table with the use of withDefaultSchema() method
	Otherwise use data source and inject the object to fire queries on user defined tables
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSrc)
				.usersByUsernameQuery("select username, password, enabled from user_accounts where username = ?")
				.authoritiesByUsernameQuery("select username, role from user_accounts where username = ?")
				.passwordEncoder(bCryptEncoder);
		}

*	PointCut->where, within(packageName.->class.->methods*->everything else)
		@Pointcut("within(com.iamdrjsolanki.pma.controllers..*)")
		public void definePackagePointCuts() {
		}
	
*	After is the Advice(After, Before, Around, AfterReturning, AfterThrowing) & takes the PointCut methods as arguments
		@After("definePackagePointCuts()")
		public void logAfter(JoinPoint jp) {
		}

*	It deletes the dependencies with projects while updating, so patch verb is used
		@PutMapping(path="/{id}", consumes="application/json")
		@ResponseStatus(HttpStatus.OK)
		public Employee updateEmployee(@RequestBody Employee emp) {
			return empServ.save(emp);
		}

*	It deletes the dependencies with employees while updating, so patch verb is used	
		@PutMapping(path="/{id}", consumes="application/json")
		@ResponseStatus(HttpStatus.OK)
		public Employee updateEmployee(@RequestBody Employee emp) {
			return empServ.save(emp);
		}

*	Spring Data Rest (RestRepositories in WebStarters) gives all the data as JSON without creating a RestController with all functionalities. Using @RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployees") Annotation on the Repository


## Running unit tests
Added testing profile and test scenarios for example, could be added more as per requirements or practice.


## Running end-to-end tests


## Further help
Please contribute to the project, by adding new features.
