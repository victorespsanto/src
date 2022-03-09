package br.com.albacares.bluefood.application.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.albacares.bluefood.domain.pedido.Pedido;
import br.com.albacares.bluefood.domain.pedido.PedidoRepository;
import br.com.albacares.bluefood.domain.pedido.RelatorioItemFaturamento;
import br.com.albacares.bluefood.domain.pedido.RelatorioItemFilter;
import br.com.albacares.bluefood.domain.pedido.RelatorioPedidoFilter;

@Service
public class RelatorioService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> listPedidos(Integer restauranteId, RelatorioPedidoFilter filter) {
		
		Integer pedidoId = filter.getPedidoId();
		
		if (pedidoId != null) {
			Pedido pedido = pedidoRepository.findByIdAndRestaurante_Id(pedidoId, restauranteId);
			return List.of(pedido);
			}
		
		LocalDate dataInicial = filter.getDataInicial();
		LocalDate dataFinal = filter.getDataFinal();
		
		if (pedidoId == null && dataInicial == null && dataFinal == null) {
			return pedidoRepository.findAll();
		}
		
		if (dataInicial == null) {
			return List.of();
		}
		
		if (dataFinal == null ) {
			dataFinal = LocalDate.now();
		}
		
		return pedidoRepository.findByDateInterval(restauranteId, dataInicial.atStartOfDay(), dataFinal.atTime(23, 59, 59));
		
		
		
	}
	
	public List<RelatorioItemFaturamento> calcularFaturamentoItens(Integer restauranteId, RelatorioItemFilter filter) {
		
		List<Object[]> itensObj;		
		
		Integer itemId = filter.getItemId();
		LocalDate dataInicial = filter.getDataInicial();
		LocalDate dataFinal = filter.getDataFinal();
		
		if (dataInicial == null) {
			return List.of();
		}
		
		if (dataFinal == null ) {
			dataFinal = LocalDate.now();
		}
		
		if (itemId != 0) {
			itensObj = pedidoRepository.findItensForFaturamento(restauranteId, itemId, dataInicial.atStartOfDay(), dataFinal.atTime(23, 59, 59));
		} else {
			itensObj = pedidoRepository.findItensForFaturamento(restauranteId, dataInicial.atStartOfDay(), dataFinal.atTime(23, 59, 59)); 
		}
		
		List<RelatorioItemFaturamento> itens = new ArrayList<>();
		
		for (Object[] item : itensObj) {
			String nome = (String) item[0];
			Long quantidade = (Long) item[1];
			BigDecimal valor = (BigDecimal) item[2];
			
			itens.add(new RelatorioItemFaturamento(nome, quantidade, valor));
		}
		
		
		return itens;
	}
	

}
