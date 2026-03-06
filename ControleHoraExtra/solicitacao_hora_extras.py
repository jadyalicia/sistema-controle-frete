import tkinter as tk
from tkinter import ttk, messagebox, scrolledtext
import pandas as pd
from datetime import datetime
import os

# CONFIGURAÇÕES
ARQUIVO_EXCEL = r"C:\Users\logistica007\OneDrive - Aljorama Industria e Comercio\Desktop\ControleHoraExtra" # CAMINNHO ESCOLHIDO

COR_FUNDO = "#E6DFFF"
COR_FRAME = "#E6DFFF"
COR_LABEL = "#402f61"
COR_BOTAO = "#6A4BCF"
COR_BOTAO_FG = "white"
COR_BOTAO_NEG = "#CF4B4B"
COR_ENTRY = "white"

# COLABORADORES E COORDENADORES

COLABORADORES = [
        {"nome": "José Carlos", "setor": "Qualidade", "turno": "1º"},
    {"nome": "Leticia Cristina", "setor": "Logística", "turno": "ADM"},
    {"nome": "Marcio Ribeiro", "setor": "Termoformadora", "turno": "1º"}
]

COORDENADORES = {
    "Logística": "joao.moraes",
}

SETORES = list(set(c["setor"] for c in COLABORADORES))
TURNOS = list(set(c["turno"] for c in COLABORADORES))

# USUÁRIOS

USUARIOS = {
    "luciano.dantas": {"senha": "123", "tipo": "lider"},
    "joao.moraes": {"senha": "admin", "tipo": "coordenador"},

}

usuario_logado = None

# FUNÇÕES GERAIS

def criar_planilha_excel():
    """Cria a planilha Excel se não existir"""
    if not os.path.exists(ARQUIVO_EXCEL):
        df = pd.DataFrame(columns=[
            "ID","Nome_Colaborador","Setor","Turno","Data",
            "Hora_Inicio","Hora_Fim","Motivo","Status","Solicitado_por",
            "Data_Solicitacao","Aprovado_por","Data_Aprovacao"
        ])
        df.to_excel(ARQUIVO_EXCEL, sheet_name="HoraExtra", index=False)


# LOGIN

def tela_login():
    global usuario_logado

    login = tk.Tk()
    login.title("Solicitação Hora Extra")
    login.geometry("350x250+500+200")
    login.configure(bg=COR_FUNDO)

    tk.Label(login, text="Usuário", bg=COR_FUNDO, fg=COR_LABEL).pack(pady=5)
    entry_user = tk.Entry(login)
    entry_user.pack()

    tk.Label(login, text="Senha", bg=COR_FUNDO, fg=COR_LABEL).pack(pady=5)
    entry_pass = tk.Entry(login, show="*")
    entry_pass.pack()

    def autenticar():
        global usuario_logado
        nome = entry_user.get()
        senha = entry_pass.get()

        if nome in USUARIOS:
            if USUARIOS[nome]["senha"] == senha:
                usuario_logado = {"nome": nome, "tipo": USUARIOS[nome]["tipo"]}
                login.destroy()
                iniciar_sistema()
            else:
                messagebox.showerror("Erro", "Senha incorreta")
        else:
            messagebox.showerror("Erro", "Usuário não cadastrado")

    tk.Button(login, text="Entrar", bg=COR_BOTAO, fg=COR_BOTAO_FG, command=autenticar).pack(pady=20)
    login.mainloop()


# DESLOGAR

def deslogar(janela_atual):
    global usuario_logado
    usuario_logado = None
    janela_atual.destroy()
    tela_login()


# INICIAR SISTEMA

def iniciar_sistema():
    if usuario_logado["tipo"] == "lider":
        tela_lider()
    elif usuario_logado["tipo"] == "coordenador":
        tela_coordenador()


# REGISTRAR SOLICITAÇÃO

def registrar_solicitacao(nomes, setor, turno, data, hora_inicio, hora_fim, motivo):
    try:
        df = pd.read_excel(ARQUIVO_EXCEL, sheet_name="HoraExtra")
    except:
        df = pd.DataFrame(columns=[
            "ID","Nome_Colaborador","Setor","Turno","Data",
            "Hora_Inicio","Hora_Fim","Motivo","Status","Solicitado_por",
            "Data_Solicitacao","Aprovado_por","Data_Aprovacao"
        ])

    for nome in nomes:
        novo_id = 1 if df.empty else df["ID"].max() + 1
        novo = {
            "ID": novo_id,
            "Nome_Colaborador": nome,
            "Setor": setor,
            "Turno": turno,
            "Data": data,
            "Hora_Inicio": hora_inicio,
            "Hora_Fim": hora_fim,
            "Motivo": motivo,
            "Status": "Pendente",
            "Solicitado_por": usuario_logado["nome"],
            "Data_Solicitacao": datetime.now(),
            "Aprovado_por": pd.NA,
            "Data_Aprovacao": pd.NA
        }
        df = pd.concat([df, pd.DataFrame([novo])], ignore_index=True)

    with pd.ExcelWriter(ARQUIVO_EXCEL, engine="openpyxl", mode="a", if_sheet_exists="replace") as writer:
        df.to_excel(writer, sheet_name="HoraExtra", index=False)

    messagebox.showinfo("OK", "Solicitação enviada com sucesso!")


# ATUALIZAR STATUS

def atualizar_status(id_registro, novo_status):
    df = pd.read_excel(ARQUIVO_EXCEL, sheet_name="HoraExtra")
    df["Aprovado_por"] = df["Aprovado_por"].astype("string")
    df["Data_Aprovacao"] = pd.to_datetime(df["Data_Aprovacao"], errors="coerce")

    if df.loc[df["ID"] == id_registro, "Status"].values[0] != "Pendente":
        messagebox.showwarning("Erro", "Essa solicitação já foi analisada.")
        return

    df.loc[df["ID"] == id_registro, "Status"] = novo_status
    df.loc[df["ID"] == id_registro, "Aprovado_por"] = str(usuario_logado["nome"])
    df.loc[df["ID"] == id_registro, "Data_Aprovacao"] = datetime.now().replace(microsecond=0)

    with pd.ExcelWriter(ARQUIVO_EXCEL, engine="openpyxl", mode="a", if_sheet_exists="replace") as writer:
        df.to_excel(writer, sheet_name="HoraExtra", index=False)

    messagebox.showinfo("OK", f"Solicitação {novo_status}.")


# TELA DO LÍDER

def tela_lider():
    root = tk.Tk()
    root.title(f"Líder: {usuario_logado['nome']} - Solicitar Hora Extra")
    root.geometry("800x600")
    root.configure(bg=COR_FUNDO)

    frame = tk.Frame(root, bg=COR_FRAME)
    frame.pack(pady=10, padx=10, fill="both", expand=True)

    # --- Campos do formulário ---
    tk.Label(frame, text="Setor:", bg=COR_FRAME, fg=COR_LABEL).grid(row=0, column=0, sticky="w")
    combo_setor = ttk.Combobox(frame, values=SETORES, state="readonly")
    combo_setor.grid(row=1, column=0, sticky="w")

    tk.Label(frame, text="Turno:", bg=COR_FRAME, fg=COR_LABEL).grid(row=2, column=0, sticky="w")
    combo_turno = ttk.Combobox(frame, values=TURNOS, state="readonly")
    combo_turno.grid(row=3, column=0, sticky="w")

    # --- Colaboradores com scroll ---
    tk.Label(frame, text="Colaboradores:", bg=COR_FRAME, fg=COR_LABEL).grid(row=4, column=0, sticky="w")
    frame_nomes_outer = tk.Frame(frame, bg=COR_FRAME)
    frame_nomes_outer.grid(row=5, column=0, sticky="w")
    canvas = tk.Canvas(frame_nomes_outer, bg=COR_FRAME, width=250, height=150)
    scrollbar = tk.Scrollbar(frame_nomes_outer, orient="vertical", command=canvas.yview)
    canvas.configure(yscrollcommand=scrollbar.set)
    scrollbar.pack(side="right", fill="y")
    canvas.pack(side="left", fill="both", expand=True)
    frame_nomes_inner = tk.Frame(canvas, bg=COR_FRAME)
    canvas.create_window((0,0), window=frame_nomes_inner, anchor="nw")

    def atualizar_scroll(event):
        canvas.configure(scrollregion=canvas.bbox("all"))

    frame_nomes_inner.bind("<Configure>", atualizar_scroll)
    checkbox_vars = {}

    def atualizar_nomes(*args):
        for widget in frame_nomes_inner.winfo_children():
            widget.destroy()
        checkbox_vars.clear()
        setor = combo_setor.get()
        turno = combo_turno.get()
        nomes_filtrados = [
            c["nome"] for c in COLABORADORES
            if (not setor or c["setor"] == setor) and (not turno or c["turno"] == turno)
        ]
        for nome in nomes_filtrados:
            var = tk.IntVar()
            cb = tk.Checkbutton(frame_nomes_inner, text=nome, variable=var, anchor="w",
                                bg=COR_FRAME, fg=COR_LABEL, selectcolor=COR_FUNDO)
            cb.pack(fill="x")
            checkbox_vars[nome] = var

    atualizar_nomes()
    combo_setor.bind("<<ComboboxSelected>>", atualizar_nomes)
    combo_turno.bind("<<ComboboxSelected>>", atualizar_nomes)

    # --- Datas e horas ---
    tk.Label(frame, text="Data:", bg=COR_FRAME, fg=COR_LABEL).grid(row=6, column=0, sticky="w")
    entry_data = tk.Entry(frame, bg=COR_ENTRY)
    entry_data.grid(row=7, column=0, sticky="w")
    entry_data.insert(0, datetime.today().strftime("%d/%m/%Y"))

    tk.Label(frame, text="Hora Início / Hora Fim:", bg=COR_FRAME, fg=COR_LABEL).grid(row=8, column=0, sticky="w")
    entry_hora_inicio = tk.Entry(frame, width=10, bg=COR_ENTRY)
    entry_hora_fim = tk.Entry(frame, width=10, bg=COR_ENTRY)
    entry_hora_inicio.grid(row=9, column=0, sticky="w", padx=(0, 5))
    entry_hora_fim.grid(row=9, column=0, sticky="w", padx=(80, 0))

    tk.Label(frame, text="Motivo:", bg=COR_FRAME, fg=COR_LABEL).grid(row=10, column=0, sticky="w")
    text_motivo = scrolledtext.ScrolledText(frame, width=40, height=5)
    text_motivo.grid(row=11, column=0, pady=5, sticky="w")

    # --- Botões ---
    def enviar():
        nomes_selecionados = [nome for nome, var in checkbox_vars.items() if var.get()]
        setor = combo_setor.get()
        turno = combo_turno.get()
        data = entry_data.get()
        hora_inicio = entry_hora_inicio.get()
        hora_fim = entry_hora_fim.get()
        motivo = text_motivo.get("1.0", tk.END).strip()

        if not nomes_selecionados or not setor or not turno or not data or not hora_inicio or not hora_fim or not motivo:
            messagebox.showwarning("Erro", "Preencha todos os campos!")
            return

        registrar_solicitacao(nomes_selecionados, setor, turno, data, hora_inicio, hora_fim, motivo)

        # Limpar campos
        for var in checkbox_vars.values(): var.set(0)
        entry_hora_inicio.delete(0, tk.END)
        entry_hora_fim.delete(0, tk.END)
        text_motivo.delete("1.0", tk.END)

    tk.Button(frame, text="Enviar Solicitação", bg=COR_BOTAO, fg=COR_BOTAO_FG, command=enviar).grid(row=12, column=0, pady=10)
    tk.Button(root, text="Deslogar", bg=COR_BOTAO_NEG, fg="white", command=lambda: deslogar(root)).pack(side="bottom", pady=10)
    root.mainloop()

# TELA DO COORDENADOR

def tela_coordenador():
    root = tk.Tk()
    root.title(f"Coordenador: {usuario_logado['nome']} - Aprovar Hora Extra")
    root.geometry("800x400")
    root.configure(bg=COR_FUNDO)

    columns = ("ID","Nome","Setor","Data","Hora_Inicio","Hora_Fim","Horas","Motivo","Status")
    tree = ttk.Treeview(root, columns=columns, show="headings", height=15)
    for col in columns:
        tree.heading(col, text=col)
        tree.column(col, width=100 if col != "Motivo" else 200)
    tree.pack(pady=10, padx=10, fill="both", expand=True)

    # Carregar solicitações pendentes
    try:
        df = pd.read_excel(ARQUIVO_EXCEL, sheet_name="HoraExtra")
        setor_coordenador = [s for s, c in COORDENADORES.items() if c == usuario_logado["nome"]][0]
        pendentes = df[(df["Status"] == "Pendente") & (df["Setor"] == setor_coordenador)]
        for _, row in pendentes.iterrows():
            tree.insert("", tk.END, values=(
                row["ID"], row["Nome_Colaborador"], row["Setor"], row["Data"],
                row["Hora_Inicio"], row["Hora_Fim"], "", row["Motivo"], row["Status"]
            ))
    except:
        pass

    # Aprovar/Reprovar
    def aprovar():
        selected = tree.selection()
        for item in selected:
            id_sel = tree.item(item)["values"][0]
            atualizar_status(id_sel, "Aprovado")
        root.destroy()
        tela_coordenador()

    def reprovar():
        selected = tree.selection()
        for item in selected:
            id_sel = tree.item(item)["values"][0]
            atualizar_status(id_sel, "Reprovado")
        root.destroy()
        tela_coordenador()

    tk.Button(root, text="Aprovar", bg="green", fg="white", command=aprovar).pack(side="left", padx=20, pady=5)
    tk.Button(root, text="Reprovar", bg="red", fg="white", command=reprovar).pack(side="left", padx=20, pady=5)
    tk.Button(root, text="Deslogar", bg=COR_BOTAO_NEG, fg="white", command=lambda: deslogar(root)).pack(side="bottom", pady=10)

    root.mainloop()


# EXECUÇÃO PRINCIPAL

if __name__ == "__main__":
    criar_planilha_excel()
    tela_login()