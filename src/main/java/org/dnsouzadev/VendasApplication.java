package org.dnsouzadev;

import org.dnsouzadev.domain.entity.Cliente;
import org.dnsouzadev.domain.entity.Pedido;
import org.dnsouzadev.domain.entity.Produto;
import org.dnsouzadev.domain.repository.Clientes;
import org.dnsouzadev.domain.repository.Pedidos;
import org.dnsouzadev.domain.repository.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos,
            @Autowired Produtos produtos
            ) {
        return args -> {
            System.out.println("Salvando clientes");
            Cliente fulano = new Cliente(null, "Fulano", "1111111111");
            clientes.save(fulano);
            Produto produto = new Produto(null, "Cadeira", new BigDecimal("500.00"));
            produtos.save(produto);

            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

//            Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

            pedidos.findByCliente(fulano).forEach(System.out::println);



        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}