
package com.example.barangbalik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                BarangBalikApp()
            }
        }
    }
}

@Composable
fun BarangBalikApp(
    loanViewModel: LoanViewModel = viewModel()
) {
    val uiState by loanViewModel.uiState.collectAsStateWithLifecycle()

    var itemName by remember { mutableStateOf("") }
    var borrowedBy by remember { mutableStateOf("") }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        val isWide = maxWidth >= 600.dp

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "BarangBalik",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Jangan sampai lupa barang yang kamu pinjamkan!",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (isWide) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    LoanForm(
                        itemName = itemName,
                        borrowedBy = borrowedBy,
                        message = uiState.message,
                        onItemNameChange = { itemName = it },
                        onBorrowedByChange = { borrowedBy = it },
                        onAdd = {
                            if (itemName.isNotBlank() &&
                                borrowedBy.isNotBlank()
                            ) {
                                loanViewModel.addItem(
                                    itemName,
                                    borrowedBy
                                )
                                itemName = ""
                                borrowedBy = ""
                            } else {
                                loanViewModel.addItem(
                                    itemName,
                                    borrowedBy
                                )
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )

                    LoanList(
                        items = uiState.items,
                        onToggle = loanViewModel::toggleReturned,
                        onDelete = loanViewModel::deleteItem,
                        modifier = Modifier.weight(1.4f)
                    )
                }
            } else {
                LoanForm(
                    itemName = itemName,
                    borrowedBy = borrowedBy,
                    message = uiState.message,
                    onItemNameChange = { itemName = it },
                    onBorrowedByChange = { borrowedBy = it },
                    onAdd = {
                        if (itemName.isNotBlank() &&
                            borrowedBy.isNotBlank()
                        ) {
                            loanViewModel.addItem(
                                itemName,
                                borrowedBy
                            )
                            itemName = ""
                            borrowedBy = ""
                        } else {
                            loanViewModel.addItem(
                                itemName,
                                borrowedBy
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                LoanList(
                    items = uiState.items,
                    onToggle = loanViewModel::toggleReturned,
                    onDelete = loanViewModel::deleteItem,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun LoanForm(
    itemName: String,
    borrowedBy: String,
    message: String?,
    onItemNameChange: (String) -> Unit,
    onBorrowedByChange: (String) -> Unit,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Catat Barang",
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = itemName,
            onValueChange = onItemNameChange,
            label = { Text("Nama barang") },
            placeholder = { Text("Contoh: Charger") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = borrowedBy,
            onValueChange = onBorrowedByChange,
            label = { Text("Dipinjam oleh") },
            placeholder = { Text("Contoh: Rina") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = onAdd,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tambah Catatan")
        }

        if (message != null) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun LoanList(
    items: List<LoanItem>,
    onToggle: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Daftar Barang (${items.size})",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (items.isEmpty()) {
            Text(
                text = "Belum ada barang yang dicatat.",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    items = items,
                    key = { it.id }
                ) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = item.isReturned,
                                onCheckedChange = {
                                    onToggle(item.id)
                                }
                            )

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = item.itemName,
                                    style = MaterialTheme.typography.titleMedium,
                                    textDecoration =
                                        if (item.isReturned) {
                                            TextDecoration.LineThrough
                                        } else {
                                            TextDecoration.None
                                        }
                                )

                                Text(
                                    text = "Dipinjam oleh: ${item.borrowedBy}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Text(
                                    text = if (item.isReturned) {
                                        "Sudah kembali"
                                    } else {
                                        "Belum kembali"
                                    },
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }

                            TextButton(
                                onClick = { onDelete(item.id) }
                            ) {
                                Text("Hapus")
                            }
                        }
                    }
                }
            }
        }
    }
}
