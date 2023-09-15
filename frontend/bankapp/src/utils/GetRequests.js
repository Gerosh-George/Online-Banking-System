export const getAccountDetails = async (accountNumber) => {
    const response = await fetch(
        `http://localhost:8080/getAccountDetails/${accountNumber}`
    );
    const data = await response.json();

    console.log(data);
    return data;
}

