@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void testCreatePessoa() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setIdade(30);

        HttpEntity<Pessoa> request = new HttpEntity<>(pessoa, headers);
        ResponseEntity<Pessoa> response = restTemplate.postForEntity("/pessoas", request, Pessoa.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Long pessoaId = response.getBody().getId();
        Pessoa savedPessoa = pessoaRepository.findById(pessoaId).orElse(null);

        assertNotNull(savedPessoa);
        assertEquals("João", savedPessoa.getNome());
        assertEquals(30, savedPessoa.getIdade());
        assertNotNull(savedPessoa.getPosicaoFila());
    }
}
