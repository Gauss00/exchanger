function Selector () {
			$('.dropdown').click(function () {
				$(this).attr('tabindex', 1).focus();
				$(this).toggleClass('active');
				$(this).find('.dropdown-menu').slideToggle(300);
			});

			$('.dropdown').focusout(function () {
				$(this).removeClass('active');
				$(this).find('.dropdown-menu').slideUp(300);
			});

			$('.dropdown .dropdown-menu li').click(function () {
				$(this).parents('.dropdown').find('span').text($(this).text());
				$(this).parents('.dropdown').find('input').attr('value', $("p", this).text());
			});
		};


		Selector();

		$(".crypto-list li").click(function() {
			$(".crypto-input").attr('value', $("p", this).text());
			$(".crypto-span").text($("p", this).text());
		});
		$('#calculate-input-1, #calculate-input-2').bind("change keyup input click", function() {
			if (this.value.match(/[^0-9, .]/g)) {
				this.value = this.value.replace(/[^0-9, .]/g, '');
			}
		});

		let cash = "";
		let user_action = "buy";
		let current_value = "0";
		let first_input = "";
		$(".crypto-list li").click(async function () {
			first_input =  $("#calculate-input-1, #calculate-input-2").val();
			let currency = $("p", this).text();
			async function GetInfo() {
				if (currency === "USDT") {
					var price = 1;
				} else {
					let url = 'https://api.binance.com/api/v3/ticker/price?symbol='+ currency +'USDT';
					var price = "0";
					await fetch(url)
						.then((response) => response.json())
						.then((data) => {
							price = data["price"];
						});
				}

				let url2 = 'https://api.binance.com/api/v3/ticker/price?symbol=USDTRUB';
				var rub_price = "0";
				await fetch(url2)
					.then((response) => response.json())
					.then((data) => {
						rub_price = data["price"];
					});
				current_value = price * rub_price;
			};
			await GetInfo();
			if (first_input !== "") {
				if (user_action === "buy"){
					CalculateStr();
				} else if (user_action === "sell"){
					CalculateRev();
				};
			}
		});

		function CalculateStr() {
			if (user_action === "buy") {
				let input1 = $("#calculate-input-1").val();
				//let result = input1/(current_value*0.9);
				let result = input1/(current_value*1);
				$("#calculate-input-2").val(result.toFixed(6));
			} else if (user_action === "sell") {
				let input1 = $("#calculate-input-1").val();
				let result = input1/(current_value*1.15);
				$("#calculate-input-2").val(result.toFixed(6));
			}
		};

		function CalculateRev() {
			if (user_action === "buy") {
				let input1 = $("#calculate-input-2").val();
				//let result = input1*(current_value*0.9);
				let result = input1*(current_value*1);
				$("#calculate-input-1").val(result.toFixed(2));
			} else if (user_action === "sell") {
				let input1 = $("#calculate-input-2").val();
				let result = input1*(current_value*1.15);
				$("#calculate-input-1").val(result.toFixed(2));
			}
		};

		$("#calculate-input-1, #calculate-input-2").bind("change keyup input click",function(){
			cash = $("#the-cash").val();
			if (current_value === "0" || cash === "") {
				$(this).val("");
				if (current_value === "0" && cash === "") {
					$("#choose-the-crypto, #choose-the-cash").addClass("error");
				}else if (current_value === "0") {
					$("#choose-the-crypto").addClass("error");
				} else if (cash === "") {
					$("#choose-the-cash").addClass("error");
				}
				setTimeout(() => {
					$("#choose-the-crypto, #choose-the-cash").removeClass("error");
				}, 2000);
				return;
			} else {
				if ($(this).attr("id") === "calculate-input-1"){
					CalculateStr();
				} else if ($(this).attr("id") === "calculate-input-2"){
					CalculateRev();
				}
			}
		});

