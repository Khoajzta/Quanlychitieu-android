fun formatCurrency(amount: Int): String {
    return "%,dđ".format(amount).replace(',', '.')
}