// URL API Spring Boot
const API_URL = "/api/ktp";

$(document).ready(function() {

    loadDataKtp();

    $("#ktpForm").submit(function(event) {
        event.preventDefault();

        let id = $("#ktpId").val();
        let method = id ? "PUT" : "POST";
        let url = id ? `${API_URL}/${id}` : API_URL;

        let dataKtp = {
            nomorKtp: $("#nomorKtp").val(),
            namaLengkap: $("#namaLengkap").val(),
            alamat: $("#alamat").val(),
            tanggalLahir: $("#tanggalLahir").val(),
            jenisKelamin: $("#jenisKelamin").val()
        };

        $.ajax({
            url: url,
            type: method,
            contentType: "application/json",
            data: JSON.stringify(dataKtp),
            success: function(response) {
                showMessage(id ? "Data KTP berhasil diperbarui!" : "Data KTP baru berhasil ditambahkan!", "msg-success");
                resetForm();
                loadDataKtp();
            },
            error: function(xhr) {
                let errorMsg = xhr.responseText || "Terjadi kesalahan pada server.";
                showMessage("Gagal: " + errorMsg, "msg-error");
            }
        });
    });

    $("#btnCancel").click(function() {
        resetForm();
    });
});

function loadDataKtp() {
    $.get(API_URL, function(data) {
        let rows = "";
        if(data.length === 0) {
            rows = `<tr><td colspan="7" style="text-align: center;">Belum ada data KTP terdaftar.</td></tr>`;
        } else {
            $.each(data, function(index, ktp) {
                rows += `<tr>
                    <td>${index + 1}</td>
                    <td>${ktp.nomorKtp}</td>
                    <td>${ktp.namaLengkap}</td>
                    <td>${ktp.alamat}</td>
                    <td>${ktp.tanggalLahir}</td>
                    <td>${ktp.jenisKelamin}</td>
                    <td>
                        <button class="btn-warning" onclick="editData(${ktp.id})">Edit</button>
                        <button class="btn-danger" onclick="deleteData(${ktp.id})">Hapus</button>
                    </td>
                </tr>`;
            });
        }
        $("#ktpTableBody").html(rows);
    });
}

function editData(id) {
    $.get(`${API_URL}/${id}`, function(ktp) {
        $("#formTitle").text("Edit Data KTP");
        $("#ktpId").val(ktp.id);
        $("#nomorKtp").val(ktp.nomorKtp);
        $("#namaLengkap").val(ktp.namaLengkap);
        $("#alamat").val(ktp.alamat);
        $("#tanggalLahir").val(ktp.tanggalLahir);
        $("#jenisKelamin").val(ktp.jenisKelamin);

        $("#btnSubmit").text("Update Data").removeClass("btn-primary").addClass("btn-warning");
        $("#btnCancel").show();
    });
}

function deleteData(id) {
    if(confirm("Yakin ingin menghapus data KTP ini?")) {
        $.ajax({
            url: `${API_URL}/${id}`,
            type: "DELETE",
            success: function() {
                showMessage("Data KTP berhasil dihapus!", "msg-success");
                loadDataKtp();
            },
            error: function() {
                showMessage("Gagal menghapus data KTP.", "msg-error");
            }
        });
    }
}

function resetForm() {
    $("#ktpForm")[0].reset();
    $("#ktpId").val("");
    $("#formTitle").text("Tambah Data KTP");
    $("#btnSubmit").text("Simpan Data").removeClass("btn-warning").addClass("btn-primary");
    $("#btnCancel").hide();
}

function showMessage(msg, typeClass) {
    let msgBox = $("#message");
    msgBox.text(msg).removeClass("msg-success msg-error").addClass(typeClass).fadeIn();

    setTimeout(function() {
        msgBox.fadeOut();
    }, 3000);
}