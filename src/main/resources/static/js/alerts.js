$(document).ready(function() {

	var status = document.getElementById("status").value;

	if (status == "email-exist") {
		swal({
			title: "خطأ!",
			text: "عنوان البريد الإلكتروني هذا موجود بالفعل.",
			icon: "error",
			button: "حاول مرة أخرى"
		});
	}

	if (status == "role-not-select") {
		swal({
			title: "خطأ!",
			text: "الرجاء اختيار دورك.",
			icon: "error",
			button: "حاول مرة أخرى"
		});
	}

	if (status == "cp-empty") {
		swal({
			title: "خطأ!",
			text: "كلمة المرور المؤكدة مطلوبة.",
			icon: "error",
			button: "حاول مرة أخرى"
		});
	}

	if (status == "cp-not-match") {
		swal({
			title: "خطأ!",
			text: "يجب أن تتطابق كلمتا المرور.",
			icon: "error",
			button: "حاول مرة أخرى"
		});
	}

	if (status == "went-wrong") {
		swal({
			title: "خطأ!",
			text: "حدث خطأ! الرجاء المحاولة لاحقًا.",
			icon: "error",
			button: "حسنًا"
		});

	}

	if (status == "registered-success") {
		swal({
			title: "نجاح!",
			text: "تم التسجيل بنجاح، قم بتسجيل الدخول الآن!",
			icon: "success",
			button: "تسجيل الدخول الآن"
		}).then(function() {
			window.location = "login";
		});
	}

	if (status == "bad-credentials") {
		swal({
			title: "بيانات الاعتماد غير صحيحة!",
			text: "عنوان البريد الإلكتروني أو كلمة المرور غير صحيح!",
			icon: "error",
			button: "حسنًا"
		});
	}

	if (status == "user-disabled") {
		swal({
			title: "تم تعليق الحساب!",
			text: "لقد قام المسؤول بتعليق حسابك.",
			icon: "error",
			button: "اتصل بنا"
		}).then(function() {

			window.location = "/contact";

		});

	}

	if (status == "logout-success") {
		swal({
			title: "تم تسجيل الخروج بنجاح!",
			icon: "success",
			button: "شكرًا!"
		});
	}

	if (status == "suspended") {
		swal({
			title: "تم تعليق المستخدم بنجاح!",
			icon: "success",
			button: "حسنًا!"
		});
	}

	if (status == "unsuspended") {
		swal({
			title: "تم إلغاء تعليق المستخدم بنجاح",
			icon: "success",
			button: "حسنًا!"
		});
	}

	if (status == "user-deleted") {

		swal({
			title: "تم حذف المستخدم بنجاح!",
			icon: "success",
			button: "حسنًا!"
		});

	}

	if (status == "title-null") {
		swal({
			title: "خطأ",
			icon: "error",
			text: "عنوان الفئة مطلوب",
			button: "حسنًا!"
		});
	}

	if (status == "category-already-exist") {
		swal({
			title: "خطأ",
			icon: "error",
			text: "الفئة بهذا الاسم موجودة بالفعل.",
			button: "حاول مرة أخرى"

		});
	}

	if(status == "category-added") {
		swal({
			title: "نجاح",
			icon: "success",
			text: "تمت إضافة الفئة بنجاح.",
			button: "حسنًا",
		});
	}

	if(status == "product-has-errors") {
		swal({
			title: "خطأ",
			text:"غير قادر على إضافة المنتج، افتح عرض المنتج لرؤية الأخطاء.",
			icon:"error",
			button: "حسنًا",
		});
	}

	if(status == "category-not-selected") {
		swal({

			title: "خطأ",
			text:"لم يتم اختيار فئة المنتج.",
			icon:"error",
			button: "حسنًا",

		});
	}

	if(status == "product-added") {
		swal({
			title: "نجاح",
			text: "تمت إضافة المنتج بنجاح",
			icon:"success",
			button: "حسنًا",
		});
	}

	if(status == "images-exceed") {
		swal({
			title : "خطأ",
			text: "لا يمكنك تحميل أكثر من 5 صور للمنتج.",
			icon : "error",
			button : "حسنًا",
		})
	}

	if(status == "user-not-exist") {
		swal({

			title : "خطأ",
			text : "المستخدم بعنوان البريد الإلكتروني المقدم غير موجود.",
			icon : "error",
			button : "حسنًا",

		});
	}

	if(status == "user-not-suspend") {
		swal({
			title : "المستخدم غير محظور.",
			text : "المستخدم بعنوان البريد الإلكتروني المقدم غير محظور.",
			icon : "info",
			button : "حسنًا",

		});
	}

	if(status == "message-send-successfully") {
		swal({
			title : "نجاح",
			text : "تم إرسال الرسالة بنجاح.",
			icon : "success",
			button : "شكرًا!",
		});
	}

	if(status == "not-login") {
		swal({
			title : "خطأ",
			text : "الرجاء تسجيل الدخول أو التسجيل للمتابعة.",
			icon : "error",
			button : "حسنًا!"
		})
	}

	if(status == "seller-not-allow") {
		swal({
			title : "خطأ",
			text: "غير مسموح للبائع بتقديم طلب.",
			icon : "error",
			button : "حسنًا",
		});
	}

	if(status == "admin-not-allow") {
		swal({
			title : "خطأ",
			text: "غير مسموح للمسؤول بتقديم طلب.",
			icon : "error",
			button : "حسنًا",
		});
	}

	if(status == "commented-successfully") {
		showToast("تمت إضافة التقييم بنجاح");
	}

	if(status == "ordered-successfully") {
		showToast("تم الطلب بنجاح. يرجى النظر في حالة الطلب، شكرًا!");
		localStorage.removeItem("cart");
	}

	if(status == "already-ordered") {
		showToast("لقد قمت بالفعل بطلب هذا العنصر. يرجى الانتظار حتى يتم معالجته.");
	}

});

function deleteUser(id) {

	swal({
		title: "هل أنت متأكد؟",
		text: "هل تريد حذف هذا المستخدم؟",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {

				window.location = "action?user=delete&user_id=" + id;

			} else {

			}
		});

}

function categoryViewOrAdd() {

	swal({
		title: "الفئات",
		text: "ماذا تريد القيام به؟ إضافة أو عرض الفئات",
		icon: "info",
		buttons: ["عرض", "إضافة"],
		dangerMode: true,
	}).then((addCategory) => {
		if (addCategory) {
			$('#category_add').modal('toggle');
		} else {
			$('#category_view').modal('toggle');
		}
	});

}

function showToast(content) {

	$("#snackbar").addClass("show");
	$("#snackbar").html(content);

	// بعد 3 ثوانٍ، قم بإزالة الفئة show من الديف
	setTimeout(() => {
		$("#snackbar").removeClass("show");
	}, 3000);
}
