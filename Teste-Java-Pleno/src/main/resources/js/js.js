		$(document).ready(function() {
			var $seuCampoCpf = $("#cpf");
			var $campoCep = $("#cep");
			$seuCampoCpf.mask('000.000.000-00', {
				reverse : true
			});

			$campoCep.mask('00.000-000', {
				reverse : true
			});
		});

		$(document)
				.ready(
						function() {
							var SPMaskBehavior = function(val) {
								return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000'
										: '(00) 0000-00009';
							}, spOptions = {
								onKeyPress : function(val, e, field, options) {
									field.mask(SPMaskBehavior.apply({},
											arguments), options);
								}
							};

							$('#telefone').mask(SPMaskBehavior, spOptions);
						});

		$(document)
				.ready(
						function() {

							function limpa_formulário_cep() {
								// Limpa valores do formulário de cep.
								$("#rua").val("");
								$("#bairro").val("");
								$("#cidade").val("");
								$("#uf").val("");
							}
							;

							$("#cep")
									.blur(
											function() {

												//Nova variável "cep" somente com dígitos.
												var cep = $(this).val()
														.replace(/\D/g, '');

												//Verifica se campo cep possui valor informado.
												if (cep != "") {

													//Expressão regular para validar o CEP.
													var validacep = /^[0-9]{8}$/;

													//Valida o formato do CEP.
													if (validacep.test(cep)) {

														//Preenche os campos com "..." enquanto consulta webservice.
														$("#rua").val("...");
														$("#bairro").val("...");
														$("#cidade").val("...");
														$("#uf").val("...");
														$("#ibge").val("...");

														//Consulta o webservice viacep.com.br/
														$
																.getJSON(
																		"https://viacep.com.br/ws/"
																				+ cep
																				+ "/json/?callback=?",
																		function(
																				dados) {

																			if (!("erro" in dados)) {
																				//Atualiza os campos com os valores da consulta.
																				$(
																						"#rua")
																						.val(
																								dados.logradouro);
																				$(
																						"#bairro")
																						.val(
																								dados.bairro);
																				$(
																						"#cidade")
																						.val(
																								dados.localidade);
																				$(
																						"#uf")
																						.val(
																								dados.uf);
																				$(
																						"#ibge")
																						.val(
																								dados.ibge);
																			} //end if.
																			else {
																				//CEP pesquisado não foi encontrado.
																				limpa_formulário_cep();
																				alert("CEP não encontrado.");
																			}
																		});
													} //end if.
													else {
														//cep é inválido.
														limpa_formulário_cep();
														alert("Formato de CEP inválido.");
													}
												} //end if.
												else {
													//cep sem valor, limpa formulário.
													limpa_formulário_cep();
												}
											});
						});